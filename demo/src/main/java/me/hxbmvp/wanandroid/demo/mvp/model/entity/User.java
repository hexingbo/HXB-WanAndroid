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
package me.hxbmvp.wanandroid.demo.mvp.model.entity;

import org.jetbrains.annotations.NotNull;

/**
 * @作者： HeXingBo
 * @时间： 2023/4/10
 * @描述： 实体类
 */
public class User {
    private final int id;
    private final String login;
    private final String avatar_url;

    public User(int id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatarUrl;
    }

    public String getAvatarUrl() {
        if (avatar_url.isEmpty()) {
            return avatar_url;
        }
        return avatar_url.split("\\?")[0];
    }


    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    @NotNull
    @Override
    public String toString() {
        return "id -> " + id + " login -> " + login;
    }
}
