package me.hxbmvp.wanandroid.demo.mvp.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;
import org.yczbj.ycrefreshviewlib.holder.BaseViewHolder;

import me.hxbmvp.wanandroid.demo.mvp.model.entity.KnowledgeHierarchyData;
import me.hxbmvp.wanandroid.demo.mvp.ui.holder.KnowledgeHierarchyViewHolder;

public class KnowledgeHierarchyAdapter extends RecyclerArrayAdapter<KnowledgeHierarchyData> {

    private KnowledgeHierarchyViewHolder viewHolder;

    public KnowledgeHierarchyAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new KnowledgeHierarchyViewHolder(parent);
        return viewHolder;
    }

    public KnowledgeHierarchyViewHolder getViewHolder() {
        return viewHolder;
    }

}
