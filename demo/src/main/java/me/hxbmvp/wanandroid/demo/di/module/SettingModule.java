package me.hxbmvp.wanandroid.demo.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import me.hxbmvp.wanandroid.demo.mvp.contract.SettingContract;
import me.hxbmvp.wanandroid.demo.mvp.model.SettingModel;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@Module
public abstract class SettingModule {

    @Binds
    abstract SettingContract.Model bindSettingModel(SettingModel model);
}