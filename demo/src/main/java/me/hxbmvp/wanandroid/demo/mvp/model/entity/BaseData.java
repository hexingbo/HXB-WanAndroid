package me.hxbmvp.wanandroid.demo.mvp.model.entity;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author: HeXingBo
 * @date: 2023/4/11
 * @description：
 */
public class BaseData implements Serializable {


    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
