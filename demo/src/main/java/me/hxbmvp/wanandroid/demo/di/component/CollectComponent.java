package me.hxbmvp.wanandroid.demo.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.hxbmvp.wanandroid.demo.di.module.CollectModule;
import me.hxbmvp.wanandroid.demo.mvp.contract.CollectContract;

import com.jess.arms.di.scope.FragmentScope;

import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.CollectFragment;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@FragmentScope
@Component(modules = CollectModule.class, dependencies = AppComponent.class)
public interface CollectComponent {
    void inject(CollectFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CollectComponent.Builder view(CollectContract.View view);

        CollectComponent.Builder appComponent(AppComponent appComponent);

        CollectComponent build();
    }
}