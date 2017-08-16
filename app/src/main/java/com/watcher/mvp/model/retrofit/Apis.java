package com.watcher.mvp.model.retrofit;

import io.reactivex.Flowable;
import retrofit2.http.POST;


public interface Apis {

    String HOST = "http://app.com/rest/";

    @POST("login")
    Flowable<String> login();

}
