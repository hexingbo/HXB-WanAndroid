package me.hxbmvp.wanandroid.demo.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.hxbmvp.wanandroid.demo.di.module.WxArticleModule;
import me.hxbmvp.wanandroid.demo.mvp.contract.WxArticleContract;

import com.jess.arms.di.scope.FragmentScope;

import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.WxArticleFragment;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@FragmentScope
@Component(modules = WxArticleModule.class, dependencies = AppComponent.class)
public interface WxArticleComponent {
    void inject(WxArticleFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        WxArticleComponent.Builder view(WxArticleContract.View view);

        WxArticleComponent.Builder appComponent(AppComponent appComponent);

        WxArticleComponent build();
    }
}