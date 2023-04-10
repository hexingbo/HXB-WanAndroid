package me.hxbmvp.wanandroid.demo.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.BaseLazyLoadFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import me.hxbmvp.wanandroid.demo.di.component.DaggerKnowledgeHierarchyComponent;
import me.hxbmvp.wanandroid.demo.mvp.contract.KnowledgeHierarchyContract;
import me.hxbmvp.wanandroid.demo.mvp.presenter.KnowledgeHierarchyPresenter;

import me.hxbmvp.wanandroid.demo.R;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * @作者：hexingbo
 * @时间：04/10/2023
 * @描述：知识体系
 */
public class KnowledgeHierarchyFragment extends BaseLazyLoadFragment<KnowledgeHierarchyPresenter> implements KnowledgeHierarchyContract.View {

    public static KnowledgeHierarchyFragment newInstance() {
        KnowledgeHierarchyFragment fragment = new KnowledgeHierarchyFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerKnowledgeHierarchyComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_knowledge_hierarchy, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

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

    }

    @Override
    protected void lazyLoadData() {

    }
}
