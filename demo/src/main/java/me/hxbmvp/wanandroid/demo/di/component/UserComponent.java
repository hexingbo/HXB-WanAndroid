/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.hxbmvp.wanandroid.demo.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;
import me.hxbmvp.wanandroid.demo.mvp.contract.UserContract;
import me.hxbmvp.wanandroid.demo.mvp.ui.activity.UserActivity;
import me.hxbmvp.wanandroid.demo.di.module.UserModule;

/**
 * @作者： HeXingBo
 * @时间： 2023/4/10 
 * @描述： 展示 Component 的用法
 */
@ActivityScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {
    void inject(UserActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        UserComponent.Builder view(UserContract.View view);

        UserComponent.Builder appComponent(AppComponent appComponent);

        UserComponent build();
    }
}
