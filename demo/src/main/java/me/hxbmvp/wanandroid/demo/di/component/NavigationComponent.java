package me.hxbmvp.wanandroid.demo.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.hxbmvp.wanandroid.demo.di.module.NavigationModule;
import me.hxbmvp.wanandroid.demo.mvp.contract.NavigationContract;

import com.jess.arms.di.scope.FragmentScope;

import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.NavigationFragment;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@FragmentScope
@Component(modules = NavigationModule.class, dependencies = AppComponent.class)
public interface NavigationComponent {
    void inject(NavigationFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        NavigationComponent.Builder view(NavigationContract.View view);

        NavigationComponent.Builder appComponent(AppComponent appComponent);

        NavigationComponent build();
    }
}