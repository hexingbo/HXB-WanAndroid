package me.hxbmvp.wanandroid.demo.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jess.arms.base.BaseLazyLoadFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.yc.cn.ycbannerlib.banner.BannerView;
import com.yc.cn.ycbannerlib.banner.adapter.AbsStaticPagerAdapter;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;
import org.yczbj.ycrefreshviewlib.inter.InterItemView;
import org.yczbj.ycrefreshviewlib.inter.OnErrorListener;
import org.yczbj.ycrefreshviewlib.inter.OnLoadMoreListener;
import org.yczbj.ycrefreshviewlib.inter.OnNoMoreListener;
import org.yczbj.ycrefreshviewlib.view.YCRefreshView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.hxbmvp.wanandroid.demo.R;
import me.hxbmvp.wanandroid.demo.di.component.DaggerMainPagerComponent;
import me.hxbmvp.wanandroid.demo.mvp.contract.MainPagerContract;
import me.hxbmvp.wanandroid.demo.mvp.model.entity.BannerData;
import me.hxbmvp.wanandroid.demo.mvp.presenter.MainPagerPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：首页
 */
public class MainPagerFragment extends BaseLazyLoadFragment<MainPagerPresenter> implements MainPagerContract.View {

    @Inject
    RecyclerView.LayoutManager mLayoutManager;
    @Inject
    RecyclerArrayAdapter mAdapter;
    @Inject
    List<BannerData> mBannerDatas;

    @BindView(R.id.recyclerView)
    YCRefreshView mRefreshView;

    private BannerView banner;
    private AppComponent mAppComponent;

    public static MainPagerFragment newInstance() {
        MainPagerFragment fragment = new MainPagerFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMainPagerComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_pager, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(getContext());
        initBanner();
        initRefreshView();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    protected void lazyLoadData() {
        mPresenter.loadRefreshData();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (banner != null) {
            //停止轮播
            banner.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null) {
            //开始轮播
            banner.resume();
        }
    }

    @Override
    public View initBanner() {
        LinearLayout mHeaderGroup = ((LinearLayout) LayoutInflater.from(getActivity()).inflate( R.layout.view_home_banner, null));
        banner = (BannerView) mHeaderGroup.findViewById(R.id.banner);
        //设置轮播时间
        banner.setPlayDelay(2000);
        //设置轮播图适配器，必须
        banner.setAdapter(new AbsStaticPagerAdapter() {
            @Override
            public View getView(ViewGroup container, int position) {
                ImageView view = new ImageView(container.getContext());
                view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                mAppComponent.imageLoader().loadImage(getContext(),
                        ImageConfigImpl
                                .builder()
                                .url(mBannerDatas.get(position).getImagePath())
                                .imageView(view)
                                .build());
                return view;
            }

            @Override
            public int getCount() {
                return mBannerDatas.size();
            }
        });
        //设置位置
        banner.setHintGravity(1);
        //设置指示器样式
        banner.setHintMode(BannerView.HintMode.TEXT_HINT);
        //判断轮播是否进行
        boolean playing = banner.isPlaying();
        //轮播图点击事件
        banner.setOnBannerClickListener(new BannerView.OnBannerClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), position + "被点击呢", Toast.LENGTH_SHORT).show();
            }
        });
        //轮播图滑动事件
        banner.setOnPageListener(new BannerView.OnPageListener() {
            @Override
            public void onPageChange(int position) {

            }
        });
        banner.resume();
        return mHeaderGroup;
    }

    @Override
    public void addHeadView() {
        mAdapter.removeAllHeader();
        InterItemView interItemView = new InterItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return initBanner();
            }

            @Override
            public void onBindView(View headerView) {
            }
        };
        mAdapter.addHeader(interItemView);
    }

    @Override
    public void initRefreshView() {
        mRefreshView.setLayoutManager(mLayoutManager);
        mRefreshView.setAdapterWithProgress(mAdapter);
        //设置刷新listener
        mRefreshView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新操作
                mPresenter.loadRefreshData();
            }
        });
        //设置是否刷新
        mRefreshView.setRefreshing(false);
        //设置刷新颜色
        mRefreshView.setRefreshingColorResources(R.color.colorAccent);
        mRefreshView.getErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshView.showProgress();
                mPresenter.loadRefreshData();
            }
        });
        mAdapter.setMore(R.layout.view_more, new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMoreData();
            }
        });
        mAdapter.setNoMore(R.layout.view_nomore, new OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {

            }

            @Override
            public void onNoMoreClick() {
                Log.e("逗比", "没有更多数据了");
            }
        });
        mAdapter.setError(R.layout.view_error, new OnErrorListener() {
            @Override
            public void onErrorShow() {
            }

            @Override
            public void onErrorClick() {
                mAdapter.resumeMore();
            }
        });
    }

    @Override
    public void showRecycler() {
        mRefreshView.showRecycler();
    }

    @Override
    public void showEmpty() {
        mRefreshView.showEmpty();
    }

    @Override
    public void showError() {
        mRefreshView.showError();
    }
}
