package me.hxbmvp.wanandroid.demo.mvp.ui.holder;

import android.text.Html;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.ThirdViewUtil;

import org.yczbj.ycrefreshviewlib.holder.BaseViewHolder;

import butterknife.BindView;
import me.hxbmvp.wanandroid.demo.R;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.FeedArticleData;


public class FeedArticleViewHolder extends BaseViewHolder<FeedArticleData> {

    @BindView(R.id.item_search_pager_author)
    TextView itemSearchPagerAuthor;
    @BindView(R.id.item_search_pager_chapterName)
    TextView itemSearchPagerChapterName;
    @BindView(R.id.item_search_pager_title)
    TextView itemSearchPagerTitle;
    @BindView(R.id.item_search_pager_tag_red_tv)
    TextView itemSearchPagerTagRedTv;
    @BindView(R.id.item_search_pager_tag_green_tv)
    TextView itemSearchPagerTagGreenTv;
    @BindView(R.id.item_search_tag_group)
    LinearLayout itemSearchTagGroup;
    @BindView(R.id.item_search_pager_like_iv)
    ImageView itemSearchPagerLikeIv;
    @BindView(R.id.item_search_pager_niceDate)
    TextView itemSearchPagerNiceDate;
    @BindView(R.id.item_search_pager_group)
    CardView itemSearchPagerGroup;

    private boolean isCollectPage;
    private boolean isSearchPage;
    private boolean isNightMode;

    private AppComponent mAppComponent;
    /**
     * 用于加载图片的管理类, 默认使用 Glide, 使用策略模式, 可替换框架
     */
    private ImageLoader mImageLoader;

    public FeedArticleViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_search_pager);
        //绑定 ButterKnife
        ThirdViewUtil.bindTarget(this, itemView);
        //可以在任何可以拿到 Context 的地方, 拿到 AppComponent, 从而得到用 Dagger 管理的单例对象
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public void setData(final FeedArticleData article) {
        if (!TextUtils.isEmpty(article.getTitle())) {
            itemSearchPagerTitle.setText(Html.fromHtml(article.getTitle()));
        }
        if (article.isCollect() || isCollectPage) {
            itemSearchPagerLikeIv.setImageResource( R.drawable.icon_like);
        } else {
            itemSearchPagerLikeIv.setImageResource( R.drawable.icon_like_article_not_selected);
        }
        if (!TextUtils.isEmpty(article.getAuthor())) {
            itemSearchPagerAuthor.setText( article.getAuthor());
        }
        if (!TextUtils.isEmpty(article.getChapterName())) {
            String classifyName = article.getSuperChapterName() + " / " + article.getChapterName();
            if (isCollectPage) {
                itemSearchPagerChapterName.setText( article.getChapterName());
            } else {
                itemSearchPagerChapterName.setText( classifyName);
            }
        }
        if (!TextUtils.isEmpty(article.getNiceDate())) {
            itemSearchPagerNiceDate.setText( article.getNiceDate());
        }
        if (isSearchPage) {
            itemSearchPagerGroup.setForeground(null);
            if (isNightMode) {
                itemSearchPagerGroup.setBackground(ContextCompat.getDrawable(mAppComponent.application(), R.color.card_color));
            } else {
                itemSearchPagerGroup.setBackground(ContextCompat.getDrawable(mAppComponent.application(), R.drawable.selector_search_item_bac));
            }
        }

    }
}
