package me.hxbmvp.wanandroid.demo.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import me.hxbmvp.wanandroid.demo.mvp.contract.MainPagerContract;
import me.hxbmvp.wanandroid.demo.mvp.model.MainPagerModel;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@Module
public abstract class MainPagerModule {

    @Binds
    abstract MainPagerContract.Model bindMainPagerModel(MainPagerModel model);
}