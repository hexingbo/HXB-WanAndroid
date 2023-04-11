package me.hxbmvp.wanandroid.demo.mvp.presenter;

import android.app.Application;

import androidx.annotation.NonNull;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.RxLifecycleUtils;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.hxbmvp.wanandroid.demo.app.Constants;
import me.hxbmvp.wanandroid.demo.app.GlobalBaseObserver;
import me.hxbmvp.wanandroid.demo.app.utils.BaseObserver;
import me.hxbmvp.wanandroid.demo.app.utils.RxUtils;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.BannerData;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.BaseResponse;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleData;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleListData;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.LoginData;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import me.hxbmvp.wanandroid.demo.mvp.contract.MainPagerContract;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@FragmentScope
public class MainPagerPresenter extends BasePresenter<MainPagerContract.Model, MainPagerContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    List<FeedArticleData> mFeedArticleDatas;
    @Inject
    RecyclerArrayAdapter mAdapter;
    @Inject
    List<BannerData> mBannerDatas;

    private int page = 0;

    @Inject
    public MainPagerPresenter(MainPagerContract.Model model, MainPagerContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    @NonNull
    private HashMap<String, Object> createResponseMap(
            BaseResponse<List<BannerData>> bannerResponse,
            BaseResponse<FeedArticleListData> feedArticleListResponse) {
        HashMap<String, Object> map = new HashMap<>(3);
//        map.put(Constants.LOGIN_DATA, loginResponse);
        map.put(Constants.BANNER_DATA, bannerResponse);
        map.put(Constants.ARTICLE_DATA, feedArticleListResponse);
        return map;
    }


    public void loadRefreshData() {
        Observable<BaseResponse<List<BannerData>>> mBannerObservable = mModel.getBannerData();
        Observable<BaseResponse<FeedArticleListData>> mArticleObservable = mModel.getFeedArticleList(page = 0);
        addDispose(Observable.zip(mBannerObservable, mArticleObservable, this::createResponseMap)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<HashMap<String, Object>>(mRootView) {
                    @Override
                    public void onNext(HashMap<String, Object> map) {

                        BaseResponse<List<BannerData>> bannerResponse = RxUtils.cast(map.get(Constants.BANNER_DATA));
                        if (bannerResponse != null) {
                            mBannerDatas.clear();
                            mBannerDatas.addAll(bannerResponse.getData());
                            if (mBannerDatas.size() > 0) {
                                mRootView.addHeadView();
                            }
                        }
                        BaseResponse<FeedArticleListData> feedArticleListResponse = RxUtils.cast(map.get(Constants.ARTICLE_DATA));
                        if (feedArticleListResponse != null) {
                            setAdapterData(feedArticleListResponse.getData(), true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mRootView.showError();
                    }
                }));
    }

    public void loadMoreData() {
        mModel.getFeedArticleList(page)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(0, 0))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {

                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new GlobalBaseObserver<FeedArticleListData>(mErrorHandler) {

                    @Override
                    public void onResult(FeedArticleListData result) {
                        setAdapterData(result, false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mAdapter.pauseMore();
                    }
                });
    }

    private void setAdapterData(FeedArticleListData result, boolean pullToRefresh) {
        if (pullToRefresh) {
            mAdapter.clear();
        }
        if (result != null) {
            List<FeedArticleData> datas = result.getDatas();
            if (datas != null && datas.size() > 0) {
                mAdapter.addAll(datas);
                page++;
                if (page > result.getPageCount()) {
                    if (!pullToRefresh) {
                        mAdapter.pauseMore();
                    }
                    mRootView.showRecycler();
                }
            }
        }

        if (mAdapter.getAllData().size() == 0) {
            mRootView.showEmpty();
        }
    }
}
