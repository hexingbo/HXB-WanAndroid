package me.hxbmvp.wanandroid.demo.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;



 /**
 * @作者：hexingbo
 * @时间：04/10/2023 11:08
 * @描述： 
 */
public interface SplashContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        /**
         * 初始化动画
         */
        void initEventAndData();
        /**
         * 跳转主界面
         */
        void jumpToMain();
    }
    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel{

    }
}
