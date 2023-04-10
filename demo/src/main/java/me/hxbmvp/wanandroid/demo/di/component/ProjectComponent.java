package me.hxbmvp.wanandroid.demo.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.hxbmvp.wanandroid.demo.di.module.ProjectModule;
import me.hxbmvp.wanandroid.demo.mvp.contract.ProjectContract;

import com.jess.arms.di.scope.FragmentScope;

import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.ProjectFragment;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@FragmentScope
@Component(modules = ProjectModule.class, dependencies = AppComponent.class)
public interface ProjectComponent {
    void inject(ProjectFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ProjectComponent.Builder view(ProjectContract.View view);

        ProjectComponent.Builder appComponent(AppComponent appComponent);

        ProjectComponent build();
    }
}