package com.lovelz.lzmvvm.base.model;

import com.lovelz.lzmvvm.lifecycle.SingleLiveEvent;


/**
 * 点击事件触发的不是一级item的情况下
 */
public class BaseRefreshClickChildViewModel<T, CLICK> extends BaseRefreshViewModel<T> {

    public final SingleLiveEvent<CLICK> mClickChildEvent = new SingleLiveEvent<>();

}
