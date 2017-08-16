package com.watcher.mvp.view.main.fragment;


import com.watcher.mvp.R;
import com.watcher.mvp.base.BaseFragment;
import com.watcher.mvp.presenter.MockupPresenter;
import com.watcher.mvp.presenter.contract.MockupContract;

public class MainFragment extends BaseFragment<MockupPresenter> implements MockupContract.View {


    public MainFragment() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initEventAndData() {
        presenter.fetch();
    }

    @Override
    public void apply() {

    }
}
