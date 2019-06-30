package com.lovelz.lzmvvm.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.lovelz.lzmvvm.utils.ClassUtils;

import javax.inject.Inject;

/**
 * MVVM Activity的基类
 */
public abstract class BaseActivity<B extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity implements BaseView<B, VM> {

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    protected B binding;

    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onBeforeCreate();
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            // 页面已被启用，但因内存不足页面被系统销毁过
            onBundleHandle(savedInstanceState);
        } else {
            // 第一次进入页面获取上个页面传递过来的数据
            Intent intent = getIntent();
            if (intent != null) {
                onIntentHandle(intent);
            }
        }
        initBinding();
        initViewModel();

        onInit();
        onListener();
    }

    /**
     * 初始化ViewModel
     */
    private void initViewModel() {
        Class<VM> viewModelClass = ClassUtils.getViewModel(this);
        if (viewModelClass == null) return;
        final VM viewModel = ViewModelProviders.of(this, mViewModelFactory).get(viewModelClass);
        this.viewModel = viewModel;
        onObserveViewModel(viewModel);
    }

    /**
     * 初始化Binding
     */
    private void initBinding() {
        binding = ClassUtils.getBinding(this, getLayoutInflater(), ((ViewGroup) findViewById(android.R.id.content)));
        if (binding != null) {
            setContentView(binding.getRoot());
            onBinding(binding);
        }
    }

    // 非standard的启动模式，第二次之后不会进入onCreate周期，转而是onNewIntent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent != null) {
            onIntentHandle(intent);
        }


        onListener();
    }

    @Override
    public void onObserveViewModel(@NonNull VM viewModel) {

    }

    @Override
    public void onBeforeCreate() {

    }

    @Override
    public void onBundleHandle(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void onInit() {

    }

    @Override
    public void onListener() {

    }

    @Override
    public void onIntentHandle(@NonNull Intent intent) {

    }

}
