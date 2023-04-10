package me.hxbmvp.wanandroid.demo.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.hxbmvp.wanandroid.demo.di.module.KnowledgeHierarchyModule;
import me.hxbmvp.wanandroid.demo.mvp.contract.KnowledgeHierarchyContract;

import com.jess.arms.di.scope.FragmentScope;

import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.KnowledgeHierarchyFragment;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@FragmentScope
@Component(modules = KnowledgeHierarchyModule.class, dependencies = AppComponent.class)
public interface KnowledgeHierarchyComponent {
    void inject(KnowledgeHierarchyFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        KnowledgeHierarchyComponent.Builder view(KnowledgeHierarchyContract.View view);

        KnowledgeHierarchyComponent.Builder appComponent(AppComponent appComponent);

        KnowledgeHierarchyComponent build();
    }
}