package me.hxbmvp.wanandroid.demo.mvp.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;
import org.yczbj.ycrefreshviewlib.holder.BaseViewHolder;

import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleData;
import me.hxbmvp.wanandroid.demo.mvp.ui.holder.FeedArticleViewHolder;


public class FeedArticleAdapter extends RecyclerArrayAdapter<FeedArticleData> {

    private FeedArticleViewHolder viewHolder;

    public FeedArticleAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder = new FeedArticleViewHolder(parent);
        return viewHolder;
    }

    public FeedArticleViewHolder getViewHolder() {
        return viewHolder;
    }

}
