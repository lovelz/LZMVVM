package com.lovelz.lzmvvm.net;

import android.accounts.NetworkErrorException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

abstract class ResultObserver<T> implements Observer<Response<T>> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Response<T> tResponse) {
        if (tResponse.isSuccessful()) {
            onSuccess(tResponse.body());
        } else {

        }
    }

    @Override
    public void onError(Throwable e) {
        try {
            if (e instanceof ConnectException ||
                    e instanceof TimeoutException ||
                    e instanceof NetworkErrorException ||
                    e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    abstract void onSuccess(T result);

    abstract void onFailure(Throwable e, boolean isNetWorkError);
}
