package me.hxbmvp.wanandroid.demo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import me.hxbmvp.wanandroid.demo.app.utils.StatusBarUtil;

import butterknife.BindView;
import me.hxbmvp.wanandroid.demo.di.component.DaggerSplashComponent;
import me.hxbmvp.wanandroid.demo.mvp.contract.SplashContract;
import me.hxbmvp.wanandroid.demo.mvp.presenter.SplashPresenter;

import me.hxbmvp.wanandroid.demo.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * @作者：hexingbo
 * @时间：04/10/2023 11:08
 * @描述：
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.one_animation)
    LottieAnimationView mOneAnimation;
    @BindView(R.id.two_animation)
    LottieAnimationView mTwoAnimation;
    @BindView(R.id.three_animation)
    LottieAnimationView mThreeAnimation;
    @BindView(R.id.four_animation)
    LottieAnimationView mFourAnimation;
    @BindView(R.id.five_animation)
    LottieAnimationView mFiveAnimation;
    @BindView(R.id.six_animation)
    LottieAnimationView mSixAnimation;
    @BindView(R.id.seven_animation)
    LottieAnimationView mSevenAnimation;
    @BindView(R.id.eight_animation)
    LottieAnimationView mEightAnimation;
    @BindView(R.id.nine_animation)
    LottieAnimationView mNineAnimation;
    @BindView(R.id.ten_animation)
    LottieAnimationView mTenAnimation;

    @Override
    protected void onDestroy() {
        cancelAnimation();
        super.onDestroy();
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.immersive(this,R.color.colorPrimary,0.5f);
        initEventAndData();
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
    public void initEventAndData() {
        startAnimation(mOneAnimation, "W.json");
        startAnimation(mTwoAnimation, "A.json");
        startAnimation(mThreeAnimation, "N.json");
        startAnimation(mFourAnimation, "A.json");
        startAnimation(mFiveAnimation, "N.json");
        startAnimation(mSixAnimation, "D.json");
        startAnimation(mSevenAnimation, "R.json");
        startAnimation(mEightAnimation, "I.json");
        startAnimation(mNineAnimation, "O.json");
        startAnimation(mTenAnimation, "D.json");
    }

    @Override
    public void jumpToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void cancelAnimation() {
        cancelAnimation(mOneAnimation);
        cancelAnimation(mTwoAnimation);
        cancelAnimation(mThreeAnimation);
        cancelAnimation(mFourAnimation);
        cancelAnimation(mFiveAnimation);
        cancelAnimation(mSixAnimation);
        cancelAnimation(mSevenAnimation);
        cancelAnimation(mEightAnimation);

        cancelAnimation(mNineAnimation);
        cancelAnimation(mTenAnimation);
    }

    private void startAnimation(LottieAnimationView mLottieAnimationView, String animationName) {
        mLottieAnimationView.setAnimation(animationName);
        mLottieAnimationView.playAnimation();
    }

    private void cancelAnimation(LottieAnimationView mLottieAnimationView) {
        if (mLottieAnimationView != null) {
            mLottieAnimationView.cancelAnimation();
        }
    }
}
