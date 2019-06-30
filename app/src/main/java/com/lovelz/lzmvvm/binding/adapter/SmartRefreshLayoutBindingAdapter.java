package com.lovelz.lzmvvm.binding.adapter;

import android.os.Message;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.lovelz.lzmvvm.base.model.BaseViewModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.ref.WeakReference;

public class SmartRefreshLayoutBindingAdapter {

    private static final int CODE = 1001;

    private static class Handler extends android.os.Handler {

        private WeakReference<SmartRefreshLayout> wr;

        private Handler(SmartRefreshLayout srl) {
            wr = new WeakReference<>(srl);
        }

        @Override
        public void handleMessage(Message msg) {
            if (wr.get() == null) return;
            if (msg.what == CODE) {
                wr.get().finishRefresh();
            }
        }
    }

    /**
     * Reloads the data when the pull-to-refresh is triggered.
     * <p>
     * Creates the {@code android:onRefresh} for a {@link SmartRefreshLayout}.
     */
    @BindingAdapter("android:onRefresh")
    public static <T extends BaseViewModel> void setSmartRefreshLayoutOnRefreshListener(final SmartRefreshLayout view,
                                                                                        final T viewModel) {
        final Handler handler = new Handler(view);

        view.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                handler.removeMessages(CODE);
                viewModel.onListRefresh();
                handler.sendEmptyMessageDelayed(CODE, viewModel.timeout() * 1000);
            }
        });
    }

}
