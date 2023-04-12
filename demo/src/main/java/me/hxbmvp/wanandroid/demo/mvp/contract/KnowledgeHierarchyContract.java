package me.hxbmvp.wanandroid.demo.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

import java.util.List;

import io.reactivex.Observable;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.BannerData;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.BaseResponse;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.KnowledgeHierarchyData;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
public interface KnowledgeHierarchyContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void initRefreshView();

        Activity getActivity();

        void showRecycler();

        void showEmpty();

        void showError();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        /**
         * 知识体系列表数据
         *
         * @return
         */
        Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData();
    }
}
