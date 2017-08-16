package com.watcher.mvp.di.module;

import com.watcher.mvp.App;
import com.watcher.mvp.model.retrofit.Apis;
import com.watcher.mvp.model.retrofit.RetrofitClient;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitClient provideRetrofitClient(Apis apis) {
        return new RetrofitClient(apis);
    }
}
