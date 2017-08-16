package com.watcher.mvp.di.component;

import com.watcher.mvp.App;
import com.watcher.mvp.di.module.AppModule;
import com.watcher.mvp.di.module.HttpModule;
import com.watcher.mvp.model.retrofit.RetrofitClient;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();

    RetrofitClient retrofitClient();
}
