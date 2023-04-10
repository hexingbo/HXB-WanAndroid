package me.hxbmvp.wanandroid.demo.di.component;

import dagger.BindsInstance;
import dagger.Component;
import com.jess.arms.di.component.AppComponent;

import me.hxbmvp.wanandroid.demo.di.module.SplashModule;
import me.hxbmvp.wanandroid.demo.mvp.contract.SplashContract;

import com.jess.arms.di.scope.ActivityScope;
import me.hxbmvp.wanandroid.demo.mvp.ui.activity.SplashActivity;   



 /**
 * @作者：hexingbo
 * @时间：04/10/2023 11:08
 * @描述： 
 */
@ActivityScope
@Component(modules = SplashModule.class, dependencies = AppComponent.class)
public interface SplashComponent {
    void inject(SplashActivity activity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        SplashComponent.Builder view(SplashContract.View view);
        SplashComponent.Builder appComponent(AppComponent appComponent);
        SplashComponent build();
    }
}