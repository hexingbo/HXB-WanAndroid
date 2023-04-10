package me.hxbmvp.wanandroid.demo.di.module;

import com.jess.arms.base.BaseLazyLoadFragment;
import com.jess.arms.di.scope.ActivityScope;

import java.util.ArrayList;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.hxbmvp.wanandroid.demo.mvp.contract.MainContract;
import me.hxbmvp.wanandroid.demo.mvp.model.MainModel;


/**
 * @作者：hexingbo
 * @时间：04/10/2023 11:33
 * @描述：
 */
@Module
public abstract class MainModule {

    @Binds
    abstract MainContract.Model bindMainModel(MainModel model);

    @ActivityScope
    @Provides
    static ArrayList<BaseLazyLoadFragment> mBaseFragment() {
        return new ArrayList<>();
    }

}