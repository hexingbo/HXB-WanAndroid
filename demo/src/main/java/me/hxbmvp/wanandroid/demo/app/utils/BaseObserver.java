package me.hxbmvp.wanandroid.demo.app.utils;

import android.text.TextUtils;

import com.jess.arms.mvp.IView;
import com.jess.arms.utils.LogUtils;

import io.reactivex.observers.ResourceObserver;
import me.hxbmvp.wanandroid.demo.app.exception.ServerException;
import retrofit2.HttpException;

/**
 * @author quchao
 * @date 2017/11/27
 *
 * @param <T>
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private IView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseObserver(IView view){
        this.mView = view;
    }

    protected BaseObserver(IView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(IView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver(IView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showMessage(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showMessage(e.toString());
        } else if (e instanceof HttpException) {
                mView.showMessage("网络异常");
        } else {
            mView.showMessage("未知错误");
            LogUtils.debugInfo(e.toString());
        }
        if (isShowError) {
            mView.showMessage(e.toString());
        }
    }
}
