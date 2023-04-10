package me.hxbmvp.wanandroid.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import me.hxbmvp.wanandroid.demo.mvp.contract.SplashContract;
import me.hxbmvp.wanandroid.demo.mvp.model.SplashModel;



 /**
 * @作者：hexingbo
 * @时间：04/10/2023 11:08
 * @描述： 
 */
@Module
public abstract class SplashModule {

    @Binds
    abstract SplashContract.Model bindSplashModel(SplashModel model);
}