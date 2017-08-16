package com.watcher.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.watcher.mvp.App;
import com.watcher.mvp.R;
import com.watcher.mvp.di.component.DaggerFragmentComponent;
import com.watcher.mvp.di.component.FragmentComponent;
import com.watcher.mvp.di.module.FragmentModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView{

    @Inject
    protected T presenter;
    protected View view;
    protected Activity activity;
    protected Context context;
    private Unbinder unbinder;
    @BindView(R.id.fl_pb)
    public FrameLayout fl_pb;

    @Override
    public void onAttach(Context context) {
        activity = (Activity) context;
        this.context = context;
        super.onAttach(context);
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    public void loading() {
        fl_pb.setVisibility(View.VISIBLE);
    }

    public void hide() {
        fl_pb.setVisibility(View.GONE);
    }

    @Override
    public void error(String error) {

    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.base_layout, null, false);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.fl_main);
        View subView = inflater.inflate(getLayoutId(), frameLayout, false);
        frameLayout.addView(subView);
        initInject();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        unbinder = ButterKnife.bind(this, view);
        initEventAndData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.detachView();
    }

    protected abstract void initInject();

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}