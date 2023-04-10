package me.hxbmvp.wanandroid.demo.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jess.arms.base.BaseLazyLoadFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;
import org.yczbj.ycrefreshviewlib.inter.OnErrorListener;
import org.yczbj.ycrefreshviewlib.inter.OnLoadMoreListener;
import org.yczbj.ycrefreshviewlib.inter.OnMoreListener;
import org.yczbj.ycrefreshviewlib.inter.OnNoMoreListener;
import org.yczbj.ycrefreshviewlib.view.YCRefreshView;

import javax.inject.Inject;

import butterknife.BindView;
import me.hxbmvp.wanandroid.demo.R;
import me.hxbmvp.wanandroid.demo.di.component.DaggerMainPagerComponent;
import me.hxbmvp.wanandroid.demo.mvp.contract.MainPagerContract;
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

    @BindView(R.id.recyclerView)
    YCRefreshView mRefreshView;

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
        initRefreshView();
        mPresenter.getMainPagers(true);

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {
        mRefreshView.showProgress();
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
        mPresenter.getMainPagers(true);
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
                mPresenter.getMainPagers(true);
            }
        });
        //设置是否刷新
        mRefreshView.setRefreshing(false);
        //设置刷新颜色
        mRefreshView.setRefreshingColorResources(R.color.colorAccent);

        mAdapter.setMore(R.layout.view_more, new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getMainPagers(false);
            }
        });
        mAdapter.setNoMore(R.layout.view_empty, new OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
                mAdapter.pauseMore();
            }

            @Override
            public void onNoMoreClick() {
                Log.e("逗比","没有更多数据了");
            }
        });
        mAdapter.setError(R.layout.view_error, new OnErrorListener() {
            @Override
            public void onErrorShow() {
                mAdapter.pauseMore();
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
