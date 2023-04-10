package me.hxbmvp.wanandroid.demo.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.hxbmvp.wanandroid.demo.di.module.MainModule;
import me.hxbmvp.wanandroid.demo.mvp.contract.MainContract;

import com.jess.arms.di.scope.ActivityScope;

import me.hxbmvp.wanandroid.demo.mvp.ui.activity.MainActivity;


/**
 * @作者：hexingbo
 * @时间：04/10/2023 11:33
 * @描述：
 */
@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainComponent.Builder view(MainContract.View view);

        MainComponent.Builder appComponent(AppComponent appComponent);

        MainComponent build();
    }
}