package me.hxbmvp.wanandroid.demo.di.module;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jess.arms.di.scope.FragmentScope;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.hxbmvp.wanandroid.demo.mvp.contract.MainPagerContract;
import me.hxbmvp.wanandroid.demo.mvp.model.MainPagerModel;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.BannerData;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleData;
import me.hxbmvp.wanandroid.demo.mvp.ui.adapter.FeedArticleAdapter;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@Module
public abstract class MainPagerModule {

    @Binds
    abstract MainPagerContract.Model bindMainPagerModel(MainPagerModel model);

    @FragmentScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(MainPagerContract.View view) {
        return new LinearLayoutManager(view.getActivity());
    }

    @FragmentScope
    @Provides
    static List<FeedArticleData> mFeedArticleDatas() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    static List<BannerData> mBannerDatas() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    static RecyclerArrayAdapter mFeedArticleAdapter(MainPagerContract.View view) {
        return new FeedArticleAdapter(view.getActivity());
    }

}