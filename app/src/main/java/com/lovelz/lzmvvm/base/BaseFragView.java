package com.lovelz.lzmvvm.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.lovelz.lzmvvm.base.model.BindingViewModel;

/**
 * 定义Fragment View相关周期
 */
public interface BaseFragView<B extends ViewDataBinding, VM extends ViewModel> extends BindingViewModel<B, VM> {

    /**
     * 处理{@link Fragment#getArguments()} 的值，如果有，才会调用
     * @param bundle
     */
    void onArgumentsHandle(@NonNull Bundle bundle);

    void onVisible();

    void onInvisible();

    void onLazyLoad();

    /**
     * 忽略{@link #isFirstLoad() }的值，强制刷新数据，<br>
     * 但仍要 {@link #isFragmentVisible()} && {@link #isPrepared()}
     */
    void setForceLoad(boolean forceLoad);

    boolean isForceLoad();

    boolean isPrepared();

    boolean isFirstLoad();

    boolean isFragmentVisible();

    boolean isInViewPager();

}
