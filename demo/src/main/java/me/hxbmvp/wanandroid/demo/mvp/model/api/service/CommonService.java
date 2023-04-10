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
package me.hxbmvp.wanandroid.demo.mvp.model.api.service;

import io.reactivex.Observable;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.BaseResponse;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleListData;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @作者： HeXingBo
 * @时间： 2023/4/10
 * @描述： 存放通用的一些 API
 */
public interface CommonService {

    @GET("/article/list/{page}/json")
    Observable<BaseResponse<FeedArticleListData>> getMainPager(@Path("page") int page);
}
