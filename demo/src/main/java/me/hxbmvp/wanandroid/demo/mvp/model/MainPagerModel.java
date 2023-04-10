package me.hxbmvp.wanandroid.demo.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.FragmentScope;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.hxbmvp.wanandroid.demo.mvp.contract.MainPagerContract;
import me.hxbmvp.wanandroid.demo.mvp.model.api.service.CommonService;
import me.hxbmvp.wanandroid.demo.mvp.model.api.service.UserService;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.BaseResponse;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleListData;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@FragmentScope
public class MainPagerModel extends BaseModel implements MainPagerContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MainPagerModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponse<FeedArticleListData>> getMainPagers(int page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class).getMainPager(page);
    }
}