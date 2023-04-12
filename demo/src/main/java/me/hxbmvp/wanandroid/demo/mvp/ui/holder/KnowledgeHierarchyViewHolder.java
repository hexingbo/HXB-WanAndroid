package me.hxbmvp.wanandroid.demo.mvp.ui.holder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.ThirdViewUtil;

import org.yczbj.ycrefreshviewlib.holder.BaseViewHolder;

import butterknife.BindView;
import me.hxbmvp.wanandroid.demo.R;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleData;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.KnowledgeHierarchyData;


public class KnowledgeHierarchyViewHolder extends BaseViewHolder<KnowledgeHierarchyData> {

    @BindView(R.id.item_knowledge_hierarchy_title)
    TextView mTitle;
    @BindView(R.id.item_knowledge_hierarchy_content)
    TextView mContent;

    private AppComponent mAppComponent;
    /**
     * 用于加载图片的管理类, 默认使用 Glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;

    public KnowledgeHierarchyViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_knowledge_hierarchy);
        //绑定 ButterKnife
        ThirdViewUtil.bindTarget(this, itemView);
        //可以在任何可以拿到 Context 的地方, 拿到 AppComponent, 从而得到用 Dagger 管理的单例对象
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(final KnowledgeHierarchyData item) {
        if (item.getName() == null) {
            return;
        }
        mTitle.setText(item.getName());
//        mTitle.setTextColor(CommonUtils.randomColor());
        if (item.getChildren() == null) {
            return;
        }
        StringBuilder content = new StringBuilder();
        for (KnowledgeHierarchyData data : item.getChildren()) {
            content.append(data.getName()).append("   ");
        }
        mContent.setText(content.toString());
    }
}
