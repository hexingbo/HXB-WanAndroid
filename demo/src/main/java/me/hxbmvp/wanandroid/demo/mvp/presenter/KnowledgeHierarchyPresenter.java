package me.hxbmvp.wanandroid.demo.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;

import java.util.List;

import javax.inject.Inject;

import me.hxbmvp.wanandroid.demo.app.GlobalBaseObserver;
import me.hxbmvp.wanandroid.demo.app.utils.RxUtils;
import me.hxbmvp.wanandroid.demo.mvp.contract.KnowledgeHierarchyContract;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleData;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleListData;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.KnowledgeHierarchyData;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@FragmentScope
public class KnowledgeHierarchyPresenter extends BasePresenter<KnowledgeHierarchyContract.Model, KnowledgeHierarchyContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    List<KnowledgeHierarchyData> mList;
    @Inject
    RecyclerArrayAdapter mAdapter;

    private int page = 0;

    @Inject
    public KnowledgeHierarchyPresenter(KnowledgeHierarchyContract.Model model, KnowledgeHierarchyContract.View rootView) {
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

    public void getKnowledgeHierarchyData(boolean pullToRefresh) {
        mModel.getKnowledgeHierarchyData()
                .doOnSubscribe(disposable -> {

                })
                .doFinally(() -> {

                })
                .compose(RxUtils.rxSchedulerHelperNet())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new GlobalBaseObserver<List<KnowledgeHierarchyData>>(mErrorHandler) {

                    @Override
                    public void onResult(List<KnowledgeHierarchyData> result) {
                        setAdapterData(result, false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mAdapter.pauseMore();
                    }
                });
    }

    /**
     * 更新列表数据
     *
     * @param datas
     * @param pullToRefresh
     */
    private void setAdapterData(List<KnowledgeHierarchyData> datas, boolean pullToRefresh) {
        if (pullToRefresh) {
            mAdapter.clear();
        }
        if (datas != null && datas.size() > 0) {
            mAdapter.addAll(datas);
            page++;
            if (page > 0) {
                if (!pullToRefresh) {
                    mAdapter.stopMore();
                }
                mRootView.showRecycler();
            }
        }

        if (mAdapter.getAllData().size() == 0) {
            mRootView.showEmpty();
        }
    }

}
