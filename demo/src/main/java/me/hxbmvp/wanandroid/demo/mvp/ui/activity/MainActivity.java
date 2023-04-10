package me.hxbmvp.wanandroid.demo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.BaseLazyLoadFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import me.hxbmvp.wanandroid.demo.R;
import me.hxbmvp.wanandroid.demo.app.Constants;
import me.hxbmvp.wanandroid.demo.di.component.DaggerMainComponent;
import me.hxbmvp.wanandroid.demo.mvp.contract.MainContract;
import me.hxbmvp.wanandroid.demo.mvp.presenter.MainPresenter;
import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.CollectFragment;
import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.KnowledgeHierarchyFragment;
import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.MainPagerFragment;
import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.NavigationFragment;
import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.ProjectFragment;
import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.SettingFragment;
import me.hxbmvp.wanandroid.demo.mvp.ui.fragment.WxArticleFragment;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * @作者：hexingbo
 * @时间：04/10/2023 11:33
 * @描述：
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @Inject
    ArrayList<BaseLazyLoadFragment> mFragments;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitleTv;
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.fragment_group)
    FrameLayout mFrameGroup;

    TextView mUserTv;

    /**
     * 记录上一次显示的fragment位置
     */
    private int mLastFgIndex;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initNavigationView();
        initFragments();
        initBottomNavigationView();
        switchFragment(0);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
        finish();
    }

    @Override
    public void initNavigationView() {
        mUserTv = mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_login_tv);
        mNavigationView.getMenu().findItem(R.id.nav_item_wan_android)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //首页
                        startMainPagerFragment();
                        return false;
                    }
                });
        mNavigationView.getMenu().findItem(R.id.nav_item_my_collect)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //收藏
                        startCollectFragment();
                        return false;
                    }
                });
        mNavigationView.getMenu().findItem(R.id.nav_item_setting)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //设置
                        startSettingFragment();
                        return false;
                    }
                });
        mNavigationView.getMenu().findItem(R.id.nav_item_logout)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //退出登录
                        return false;
                    }
                });
    }

    @Override
    public void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
                    loadPager(getString(R.string.home_pager), 0,
                            mFragments.get(0), Constants.TYPE_MAIN_PAGER);
                    break;
                case R.id.tab_knowledge_hierarchy:
                    loadPager(getString(R.string.knowledge_hierarchy), 1,
                            mFragments.get(1), Constants.TYPE_KNOWLEDGE);
                    break;
                case R.id.tab_wx_article:
                    loadPager(getString(R.string.wx_article), 2,
                            mFragments.get(2), Constants.TYPE_WX_ARTICLE);
                    break;
                case R.id.tab_navigation:
                    loadPager(getString(R.string.navigation), 3,
                            mFragments.get(3), Constants.TYPE_NAVIGATION);
                    break;
                case R.id.tab_project:
                    loadPager(getString(R.string.project), 4,
                            mFragments.get(4), Constants.TYPE_PROJECT);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    @Override
    public void initFragments() {
        mFragments.add(MainPagerFragment.newInstance());
        mFragments.add(KnowledgeHierarchyFragment.newInstance());
        mFragments.add(WxArticleFragment.newInstance());
        mFragments.add(NavigationFragment.newInstance());
        mFragments.add(ProjectFragment.newInstance());
        mFragments.add(CollectFragment.newInstance());
        mFragments.add(SettingFragment.newInstance());
    }

    @Override
    public void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }
        if (position > Constants.TYPE_PROJECT) {
            mFloatingActionButton.setVisibility(View.INVISIBLE);
            mBottomNavigationView.setVisibility(View.INVISIBLE);
        } else {
            mFloatingActionButton.setVisibility(View.VISIBLE);
            mBottomNavigationView.setVisibility(View.VISIBLE);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BaseFragment targetFg = mFragments.get(position);
        BaseFragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        if (lastFg != null) {
            ft.hide(lastFg);
        }
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commitAllowingStateLoss();
            ft.add(R.id.fragment_group, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }


    private void loadPager(String title, int position, BaseLazyLoadFragment mFragment, int pagerType) {
        mTitleTv.setText(title);
        switchFragment(position);
        mFragment.tryLoadData();
    }

    @Override
    public void startMainPagerFragment() {
        mTitleTv.setText("首页");
        mBottomNavigationView.setVisibility(View.VISIBLE);
        mBottomNavigationView.setSelectedItemId(R.id.tab_main_pager);
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void startCollectFragment() {
        mTitleTv.setText("收藏");
        switchFragment(Constants.TYPE_COLLECT);
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void startSettingFragment() {
        mTitleTv.setText("设置");
        switchFragment(Constants.TYPE_SETTING);
        mDrawerLayout.closeDrawers();
    }
}
