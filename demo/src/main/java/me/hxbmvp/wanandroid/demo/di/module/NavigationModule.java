package me.hxbmvp.wanandroid.demo.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import me.hxbmvp.wanandroid.demo.mvp.contract.NavigationContract;
import me.hxbmvp.wanandroid.demo.mvp.model.NavigationModel;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@Module
public abstract class NavigationModule {

    @Binds
    abstract NavigationContract.Model bindNavigationModel(NavigationModel model);
}