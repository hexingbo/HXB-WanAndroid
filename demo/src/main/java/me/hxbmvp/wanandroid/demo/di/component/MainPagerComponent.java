package me.hxbmvp.wanandroid.demo.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.hxbmvp.wanandroid.demo.di.module.MainPagerModule;
import me.hxbmvp.wanandroid.demo.mvp.contract.MainPagerContract;

import com.jess.arms.di.scope.FragmentScope;

import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.MainPagerFragment;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@FragmentScope
@Component(modules = MainPagerModule.class, dependencies = AppComponent.class)
public interface MainPagerComponent {
    void inject(MainPagerFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainPagerComponent.Builder view(MainPagerContract.View view);

        MainPagerComponent.Builder appComponent(AppComponent appComponent);

        MainPagerComponent build();
    }
}