package com.watcher.mvp.di.component;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.watcher.mvp.di.module.FragmentModule;
import com.watcher.mvp.di.scope.FragmentScope;
import com.watcher.mvp.view.main.fragment.MainFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(MainFragment fragment);
}
