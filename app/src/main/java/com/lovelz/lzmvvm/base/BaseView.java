package com.lovelz.lzmvvm.base;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.lovelz.lzmvvm.base.model.BindingViewModel;

/**
 * 定义Activity View相关周期
 */
public interface BaseView<B extends ViewDataBinding, VM extends ViewModel> extends BindingViewModel<B, VM> {

    /**
     * 处理上个页面传递过来的数据
     */
    void onIntentHandle(@NonNull Intent intent);

}
