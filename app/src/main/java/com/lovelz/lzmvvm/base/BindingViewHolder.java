package com.lovelz.lzmvvm.base;

import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseViewHolder;
import com.lovelz.lzmvvm.R;

public class BindingViewHolder<B extends ViewDataBinding> extends BaseViewHolder {

    public final B binding;

    public BindingViewHolder(View view) {
        super(view);
        this.binding = (B) view.getTag(R.id.BaseQuickAdapter_databinding_support);
    }

}
