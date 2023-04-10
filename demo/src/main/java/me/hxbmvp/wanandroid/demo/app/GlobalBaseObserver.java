package me.hxbmvp.wanandroid.demo.app;

import com.jess.arms.utils.LogUtils;

import me.hxbmvp.wanandroid.demo.mvp.model.entity.BaseResponse;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public abstract class GlobalBaseObserver<T extends Object> extends ErrorHandleSubscriber<BaseResponse<T>> {

    public GlobalBaseObserver(RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
    }

    @Override
    public void onNext(BaseResponse<T> resultBean) {
        LogUtils.debugInfo("-----hxb---->" + resultBean.toString());
        if (resultBean.isSuccess()) {
            //请求成功
            onResult(resultBean.getData());
        } else {
            onError(new Throwable(resultBean.getMsg()));
        }
    }

    public abstract void onResult(T result);

    @Override
    public void onError(Throwable t) {
        super.onError(t);
    }
}
