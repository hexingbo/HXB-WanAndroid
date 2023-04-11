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

import me.hxbmvp.wanandroid.demo.mvp.model.api.Api;

/**
 * @作者： HeXingBo
 * @时间： 2023/4/10
 * @描述：
 * 如果你服务器返回的数据格式固定为这种方式(这里只提供思想,服务器返回的数据格式可能不一致,可根据自家服务器返回的格式作更改)
 * 指定范型即可改变 {@code data} 字段的类型, 达到重用 {@link BaseResponse}, 如果你实在看不懂, 请忽略
 */
public class BaseResponse<T> extends BaseData {
    private T data;
    private String errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public String getCode() {
        return errorCode;
    }

    public String getMsg() {
        return errorMsg;
    }

    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return errorCode.equals(Api.REQUEST_SUCCESS);
    }
}
