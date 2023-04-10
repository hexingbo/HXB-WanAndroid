package me.hxbmvp.wanandroid.demo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import me.hxbmvp.wanandroid.demo.di.component.DaggerMainComponent;
import me.hxbmvp.wanandroid.demo.mvp.contract.MainContract;
import me.hxbmvp.wanandroid.demo.mvp.presenter.MainPresenter;

import me.hxbmvp.wanandroid.demo.R;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * @作者：hexingbo
 * @时间：04/10/2023 11:33
 * @描述：
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

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
        initBottomNavigationView();
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

    private void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
//                    loadPager(getString(R.string.home_pager), 0,
//                            mMainPagerFragment, Constants.TYPE_MAIN_PAGER);
                    break;
                case R.id.tab_knowledge_hierarchy:
//                    loadPager(getString(R.string.knowledge_hierarchy), 1,
//                            mKnowledgeHierarchyFragment, Constants.TYPE_KNOWLEDGE);
                    break;
                case R.id.tab_wx_article:
//                    loadPager(getString(R.string.wx_article), 2,
//                            mWxArticleFragment, Constants.TYPE_WX_ARTICLE);
                    break;
                case R.id.tab_navigation:
//                    loadPager(getString(R.string.navigation), 3,
//                            mNavigationFragment, Constants.TYPE_NAVIGATION);
                    break;
                case R.id.tab_project:
//                    loadPager(getString(R.string.project), 4,
//                            mProjectFragment, Constants.TYPE_PROJECT);
                    break;
                default:
                    break;
            }
            return true;
        });
    }
}
