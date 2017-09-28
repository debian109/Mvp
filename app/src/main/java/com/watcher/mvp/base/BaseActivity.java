package com.watcher.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.watcher.mvp.App;
import com.watcher.mvp.R;
import com.watcher.mvp.di.component.ActivityComponent;
import com.watcher.mvp.di.component.DaggerActivityComponent;
import com.watcher.mvp.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    protected T presenter;
    protected Activity context;
    private Unbinder unbinder;
    protected FrameLayout fl_main;
    @BindView(R.id.fl_pb)
    public FrameLayout fl_pb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.base_layout);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(getLayoutId(), fl_main, false);
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        fl_main.addView(view);
        unbinder = ButterKnife.bind(this);
        initInject();
        if (presenter != null)
            presenter.attachView(this);
        initEventAndData();
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null)
            presenter.detachView();
        unbinder.unbind();
    }

    protected abstract void initInject();
    protected abstract int getLayoutId();
    protected abstract void initEventAndData();
}