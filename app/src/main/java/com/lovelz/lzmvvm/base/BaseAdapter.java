package com.lovelz.lzmvvm.base;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lovelz.lzmvvm.R;
import com.lovelz.lzmvvm.base.model.BaseViewModel;

import java.util.List;

public abstract class BaseAdapter<T, B extends ViewDataBinding>
        extends BaseQuickAdapter<T, BindingViewHolder<B>>
        implements BaseQuickAdapter.RequestLoadMoreListener{

    private boolean loaded = false;

    public BaseAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseAdapter(@Nullable List<T> data) {
        super(data);
    }

    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        if (layoutResId != mLayoutResId) return super.getItemView(layoutResId, parent);

        B binding = DataBindingUtil.inflate(
                mLayoutInflater,
                layoutResId,
                parent,
                false);
        onBinding(binding);
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    /**
     * 已初始化Binding
     */
    protected abstract void onBinding(B binding);

    /**
     * 绑定RecyclerView
     *
     * @param load 是否需要加载
     */
    public void bindToRecyclerView(RecyclerView recyclerView, boolean load) {
        super.bindToRecyclerView(recyclerView);

        if (load)
            this.setOnLoadMoreListener(this, recyclerView);
    }

    @Override
    public void onLoadMoreRequested() {
        if (loaded) {
            this.loadMoreEnd();
            return;
        }
        BaseViewModel viewModel = getViewModel();
        if (viewModel == null) return;
        viewModel.onListLoad(viewModel.getLoadOffset());
    }

    @Nullable
    protected BaseViewModel getViewModel() {
        return null;
    }

    public void setLoaded(boolean end) {
        loaded = end;
        if (!end) {
            this.loadMoreComplete();
        }
    }

}
