package com.lovelz.lzmvvm.binding.adapter;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lovelz.lzmvvm.R;
import com.lovelz.lzmvvm.base.BaseAdapter;

import java.util.List;
import java.util.Objects;

/**
 * 与RecyclerView相关的自定义属性
 */
public class ListItemsBindingAdapter {

    @BindingAdapter("bind:items")
    public static <T, V extends BaseViewHolder> void setItems(RecyclerView recyclerView, List<T> items) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (!(adapter instanceof BaseQuickAdapter)) return;
        BaseQuickAdapter<T, V> baseAdapter = (BaseQuickAdapter<T, V>) adapter;
        baseAdapter.setNewData(items);
    }

    /**
     * 是否显示分割线
     */
    @BindingAdapter("bind:hideLine")
    public static void showLine(RecyclerView recyclerView, boolean hideLine) {
        if (hideLine) {
            if (recyclerView.getItemDecorationCount() > 0) {
                recyclerView.removeItemDecorationAt(0);
            }
        } else {
            DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
            itemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.shape_divider_line)));
            recyclerView.addItemDecoration(itemDecoration);
        }
    }

}
