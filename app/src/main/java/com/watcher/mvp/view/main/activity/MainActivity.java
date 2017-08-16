package com.watcher.mvp.view.main.activity;

import android.widget.TextView;

import com.blankj.utilcode.util.SnackbarUtils;
import com.watcher.mvp.R;
import com.watcher.mvp.base.BaseActivity;
import com.watcher.mvp.presenter.MockupPresenter;
import com.watcher.mvp.presenter.contract.MockupContract;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MockupPresenter> implements MockupContract.View {

    @BindView(R.id.tv_hello)
    TextView tv_hello;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void initEventAndData() {
        presenter.fetch();
    }

    @Override
    public void apply() {

    }

    @Override
    public void loading() {
        super.loading();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void error(String error) {
        super.error(error);
        SnackbarUtils.with(fl_main).setMessage(error).showError();
    }
}
