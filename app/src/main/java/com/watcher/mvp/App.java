package com.watcher.mvp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.watcher.mvp.di.component.AppComponent;
import com.watcher.mvp.di.component.DaggerAppComponent;
import com.watcher.mvp.di.module.AppModule;
import com.watcher.mvp.di.module.HttpModule;

import io.realm.Realm;

public class App extends Application {

    public static AppComponent appComponent;
    private static App instance;

    public static synchronized App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);
        Utils.init(this);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
