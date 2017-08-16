package com.watcher.mvp.di.component;

import android.app.Activity;
import com.watcher.mvp.di.module.ActivityModule;
import com.watcher.mvp.di.scope.ActivityScope;
import com.watcher.mvp.view.main.activity.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity activity);
}
