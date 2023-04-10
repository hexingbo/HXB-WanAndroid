package me.hxbmvp.wanandroid.demo.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.RxLifecycleUtils;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.hxbmvp.wanandroid.demo.app.GlobalBaseObserver;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleData;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleListData;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.User;
import me.hxbmvp.wanandroid.demo.mvp.ui.adapter.FeedArticleAdapter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import me.hxbmvp.wanandroid.demo.mvp.contract.MainPagerContract;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
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

    private int page = 0;
    private boolean hasNetWork = true;


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

    public void getMainPagers(boolean pullToRefresh) {
        mModel.getMainPagers(page)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                    if (pullToRefresh) {
                        mRootView.showLoading();//显示下拉刷新的进度条
                    } else {
                        mRootView.startLoadMore();//显示上拉加载更多的进度条
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh) {
                        mRootView.hideLoading();//隐藏下拉刷新的进度条
                    } else {
                        mRootView.endLoadMore();//隐藏上拉加载更多的进度条
                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new GlobalBaseObserver<FeedArticleListData>(mErrorHandler) {

                    @Override
                    public void onResult(FeedArticleListData result) {
                        if (result != null) {
                            List<FeedArticleData> datas = result.getDatas();
                            if (datas != null && datas.size() > 0) {
                                if (pullToRefresh) {
                                    mFeedArticleDatas.clear();
                                    mAdapter.clear();
                                }
                                mFeedArticleDatas.addAll(datas);
                                mAdapter.addAll(datas);
                                page++;
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);

                    }
                });
    }
}
