package com.watcher.mvp.model.retrofit;

import io.reactivex.Flowable;

public class RetrofitClient {

    private Apis apis;

    public RetrofitClient(Apis apis) {
        this.apis = apis;
    }

    public Flowable<String> login(){
        return apis.login();
    }

}
