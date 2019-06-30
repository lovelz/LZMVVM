package com.lovelz.lzmvvm.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.lovelz.lzmvvm.base.model.BaseViewModel;
import com.lovelz.lzmvvm.utils.ClassUtils;

import javax.inject.Inject;

/**
 * MVVM Fragment的基类
 */
public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements BaseFragView<B, VM> {

    @Inject
    protected ViewModelProvider.Factory mViewModelFactory;

    protected B binding;

    protected VM viewModel;

    /**
     * Fragment是否可见状态
     */
    private boolean isFragmentVisible;

    /**
     * View已经初始化完成
     */
    private boolean isPrepared;

    /**
     * 是否第一次加载
     */
    private boolean isFirstLoad = true;

    /**
     * <pre>
     * 忽略isFirstLoad的值，强制刷新数据，但仍要Visible & Prepared
     * 一般用于PagerAdapter需要刷新各个子Fragment的场景
     * 不要new 新的 PagerAdapter 而采取reset数据的方式
     * 所以要求Fragment重新走initData方法
     * 故使用 {@link #setForceLoad(boolean)}来让Fragment下次执行initData
     * </pre>
     */
    private boolean forceLoad = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        onBeforeCreate();
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            onBundleHandle(savedInstanceState);
        }

        Bundle bundle = getArguments();
        if (bundle != null && bundle.size() > 0) {
            onArgumentsHandle(bundle);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        isFirstLoad = true;
        isPrepared = true;
        if (!isInViewPager()) {
            isFragmentVisible = true;
        }

        binding = ClassUtils.getBinding(this, inflater, container);
        if (binding == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            onBinding(binding);
            return binding.getRoot();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Class<VM> viewModelClass = ClassUtils.getViewModel(this);
        if (viewModelClass == null) return;

        final VM viewModel;
        if (providerVmByActivity() && getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity(), mViewModelFactory).get(viewModelClass);
        } else {
            viewModel = ViewModelProviders.of(this, mViewModelFactory).get(viewModelClass);
        }
        this.viewModel = viewModel;
        onObserveViewModel(viewModel);

        onInit();
        onListener();
        lazyLoad();
    }


    /***
     *  如果是与ViewPager一起使用，调用的是setUserVisibleHint
     * @param isVisibleToUser 是否显示出来了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     * visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onInvisible();
        } else {
            onVisible();
        }
    }

    @Override
    public void onVisible() {
        isFragmentVisible = true;
        lazyLoad();
    }


    private void lazyLoad() {
        if (isPrepared() && isFragmentVisible()) {
            if (isForceLoad() || isFirstLoad()) {
                forceLoad = false;
                isFirstLoad = false;
                onLazyLoad();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            viewModel.onDestroy();
            viewModel = null;
        }
        if (binding != null) {
            binding.unbind();
        }
    }

    /**
     * 实例化ViewModel是否通过Activity
     */
    protected boolean providerVmByActivity() {
        return false;
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
    public void onArgumentsHandle(@NonNull Bundle bundle) {

    }

    @Override
    public void onInvisible() {
        isFragmentVisible = false;
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void setForceLoad(boolean forceLoad) {
        this.forceLoad = forceLoad;
    }

    @Override
    public boolean isForceLoad() {
        return forceLoad;
    }

    @Override
    public boolean isPrepared() {
        return isPrepared;
    }

    @Override
    public boolean isFirstLoad() {
        return isFirstLoad;
    }

    @Override
    public boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    @Override
    public boolean isInViewPager() {
        return true;
    }

}
