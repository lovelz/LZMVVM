package com.lovelz.lzmvvm.base.model;

import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    private int loadOffset = initLoadOffset();

    public void onDestroy() {

    }

    public void onListRefresh() {
        loadOffset = initLoadOffset();
    }

    public void onListLoad(int offset) {
    }

    public int getLoadOffset() {
        return ++loadOffset;
    }

    /**
     * 超时时间(单位：s)
     */
    public int timeout() {
        return 20;
    }

    public int initLoadOffset() {
        return 0;
    }

}
