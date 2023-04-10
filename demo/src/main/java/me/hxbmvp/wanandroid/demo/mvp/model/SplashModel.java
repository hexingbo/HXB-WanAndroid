package me.hxbmvp.wanandroid.demo.mvp.model;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;
import javax.inject.Inject;

import me.hxbmvp.wanandroid.demo.mvp.contract.SplashContract;

 /**
 * @作者：hexingbo
 * @时间：04/10/2023 11:08
 * @描述： 
 */
@ActivityScope
public class SplashModel extends BaseModel implements SplashContract.Model{
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SplashModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}