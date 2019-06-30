package com.lovelz.lzmvvm.helper;

import androidx.recyclerview.widget.RecyclerView;

import com.lovelz.lzmvvm.base.BaseAdapter;

public class RefreshLoadHelper {

    public static void init(BaseAdapter adapter, RecyclerView list) {
        adapter.bindToRecyclerView(list, true);
        EmptyViewHelper.initEmpty(list);
    }

//    public static  <T> Observer<RefreshLoadModel<MutableLiveData<Result<PageBean<T>>>>> listener(LifecycleOwner owner, RecyclerView recyclerView, MQQuickAdapter adapter, SwipeRefreshLayout refresh, RefreshLoadViewModel<T> viewModel) {
//        return new Observer<RefreshLoadModel<MutableLiveData<Result<PageBean<T>>>>>() {
//            @Override
//            public void onChanged(@Nullable RefreshLoadModel<MutableLiveData<Result<PageBean<T>>>> mutableLiveDataRefreshLoadModel) {
//
//                if (mutableLiveDataRefreshLoadModel == null) return;
//                if (mutableLiveDataRefreshLoadModel.isRefresh) {
//                    adapter.setEnableLoadMore(false);
//                } else {
//                    refresh.setEnabled(false);
//                }
//
//                mutableLiveDataRefreshLoadModel.data.observe(owner, new Observer<Result<PageBean<T>>>() {
//                    @Override
//                    public void onChanged(@Nullable Result<PageBean<T>> pageBeanResult) {
//                        refresh.setEnabled(true);
//                        refresh.setRefreshing(false);
//
//                        if (pageBeanResult == null && adapter.isLoading()) {
//                            adapter.loadMoreFail();
//                        } else {
//                            adapter.setEnableLoadMore(true);
//                            adapter.setLoaded(false);
//                        }
//
//                        if (pageBeanResult == null || pageBeanResult.getErrorCode() != Net.ZERO) {
//                            EmptyViewHelper.setErrEmpty(recyclerView, pageBeanResult != null ? pageBeanResult.getErrorMsg() : null);
//                            if (pageBeanResult != null && pageBeanResult.getErrorCode() == Net.NOT_LOGIN) {
//                                ToastHelper.error(App.getInstance().getString(R.string.not_login));
//                                App.Login.out();
//                            }
//                            return;
//                        }
//
//                        if (pageBeanResult.getData().isOver()) {
//                            adapter.setLoaded(true);
//                        }
//
//                        if (mutableLiveDataRefreshLoadModel.isRefresh) {
//                            viewModel.getList().clear();
//                        }
//                        viewModel.getList().addAll(pageBeanResult.getData().getDatas());
//                    }
//                });
//            }
//        };
//    }

}
