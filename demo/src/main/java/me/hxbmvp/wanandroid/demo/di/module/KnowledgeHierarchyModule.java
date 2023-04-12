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
import me.hxbmvp.wanandroid.demo.mvp.contract.KnowledgeHierarchyContract;
import me.hxbmvp.wanandroid.demo.mvp.model.KnowledgeHierarchyModel;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.KnowledgeHierarchyData;
import me.hxbmvp.wanandroid.demo.mvp.ui.adapter.KnowledgeHierarchyAdapter;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：
 */
@Module
public abstract class KnowledgeHierarchyModule {

    @Binds
    abstract KnowledgeHierarchyContract.Model bindKnowledgeHierarchyModel(KnowledgeHierarchyModel model);

    @FragmentScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(KnowledgeHierarchyContract.View view) {
        return new LinearLayoutManager(view.getActivity());
    }

    @FragmentScope
    @Provides
    static List<KnowledgeHierarchyData> mList() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    static RecyclerArrayAdapter mAdapter(KnowledgeHierarchyContract.View view) {
        return new KnowledgeHierarchyAdapter(view.getActivity());
    }

}