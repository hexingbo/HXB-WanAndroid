package me.hxbmvp.wanandroid.demo.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.hxbmvp.wanandroid.demo.di.module.SettingModule;
import me.hxbmvp.wanandroid.demo.mvp.contract.SettingContract;

import com.jess.arms.di.scope.FragmentScope;

import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.SettingFragment;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@FragmentScope
@Component(modules = SettingModule.class, dependencies = AppComponent.class)
public interface SettingComponent {
    void inject(SettingFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SettingComponent.Builder view(SettingContract.View view);

        SettingComponent.Builder appComponent(AppComponent appComponent);

        SettingComponent build();
    }
}