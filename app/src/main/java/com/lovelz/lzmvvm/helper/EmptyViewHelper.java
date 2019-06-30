package com.lovelz.lzmvvm.helper;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lovelz.lzmvvm.R;

public class EmptyViewHelper {

    public static void initEmpty(RecyclerView viewGroup) {
        if (viewGroup.getAdapter() instanceof BaseQuickAdapter) {
            ((BaseQuickAdapter) viewGroup.getAdapter()).setEmptyView(R.layout.activity_main, viewGroup);
        }
    }

    public static void setErrEmpty(RecyclerView viewGroup, String errInfo) {
        if (viewGroup.getAdapter() instanceof BaseQuickAdapter) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main, viewGroup, false);
//            TextView textView = view.findViewById(R.id.errInfo);
//            if (TextUtils.isEmpty(errInfo)) {
//                textView.setVisibility(View.GONE);
//            } else {
//                textView.setText(errInfo);
//            }
            ((BaseQuickAdapter) viewGroup.getAdapter()).setEmptyView(view);
        }
    }

}
