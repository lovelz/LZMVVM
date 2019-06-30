package com.lovelz.lzmvvm.base.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.lovelz.lzmvvm.lifecycle.SingleLiveEvent;

/**
 * 具有刷新功能的ViewModel
 */
public class BaseRefreshViewModel<T> extends BaseViewModel {

    public final ObservableList<T> items = new ObservableArrayList<>();
    public final SingleLiveEvent<T> mClickEvent = new SingleLiveEvent<>();
    /**
     * 更新时的第一页页码
     */
    public static final int UPDATE_INDEX = 0;
    /**
     * 加载初始偏移度
     */
    public static final int INIT_LOAD_OFFSET = 0;

    @Override
    public int initLoadOffset() {
        return INIT_LOAD_OFFSET;
    }
}
