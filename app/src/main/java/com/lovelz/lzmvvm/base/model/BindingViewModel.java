package com.lovelz.lzmvvm.base.model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public interface BindingViewModel<B extends ViewDataBinding, VM extends ViewModel> {

    /**
     * 实例化Binding后调用该方法
     */
    void onBinding(@NonNull B binding);


    /**
     * 实例化ViewModel后调用该方法
     */
    void onObserveViewModel(@NonNull VM viewModel);


    /**
     * 在 super {@link android.app.Activity#onCreate(Bundle)}之前被调用
     */
    void onBeforeCreate();

    /**
     * 在 super {@link android.app.Activity#onCreate(Bundle)}之前被调用，并且有Bundle
     * @param savedInstanceState 该参数不可能为null
     */
    void onBundleHandle(@NonNull Bundle savedInstanceState);

    /**
     * 写一些初始化的操作
     * 在 {@link #onObserveViewModel(ViewModel)}  之后被调用
     */
    void onInit();
    /**
     * 这里面写监听事件
     * 在 {@link #onInit()}  之后被调用
     */
    void onListener();


}
