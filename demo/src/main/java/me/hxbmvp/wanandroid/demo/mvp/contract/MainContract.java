package me.hxbmvp.wanandroid.demo.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

/**
 * @作者：hexingbo
 * @时间：04/10/2023 11:33
 * @描述：
 */
public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        /**
         * 初始化侧边栏头部信息
         */
        void initNavigationView();

        /**
         * 初始化主界面底部导航菜单
         */
        void initBottomNavigationView();

        /**
         * 初始化主界面内容区域
         */
        void initFragments();

        /**
         * 切换主界面内容页面
         *
         * @param position
         */
        void switchFragment(int position);

        /**
         * 显示主界面
         */
        void startMainPagerFragment();

        /**
         * 显示收藏页面
         */
        void startCollectFragment();

        /**
         * 显示设置页面
         */
        void startSettingFragment();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

    }
}
