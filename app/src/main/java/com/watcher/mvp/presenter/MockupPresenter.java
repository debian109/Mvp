package com.watcher.mvp.presenter;

import com.blankj.utilcode.util.NetworkUtils;
import com.watcher.mvp.base.RxPresenter;
import com.watcher.mvp.model.retrofit.RetrofitClient;
import com.watcher.mvp.presenter.contract.MockupContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MockupPresenter extends RxPresenter<MockupContract.View> implements MockupContract.Presenter {

    RetrofitClient retrofit;

    @Inject
    public MockupPresenter(RetrofitClient retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void fetch() {
        if(NetworkUtils.isConnected()){
            view.loading();
            addSubscribe(retrofit.login()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(r -> {
                        view.apply();
                        view.hide();
                    }, e -> {
                        view.error(e.getMessage());
                        view.hide();
                    })
            );
        }else {
            view.error("No network!");
        }

    }
}
