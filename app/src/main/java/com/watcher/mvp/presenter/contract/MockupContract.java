package com.watcher.mvp.presenter.contract;

import com.watcher.mvp.base.BasePresenter;
import com.watcher.mvp.base.BaseView;

public interface MockupContract {

    interface View extends BaseView {
        void apply();
    }

    interface Presenter extends BasePresenter<View> {
        void fetch();
    }
}
