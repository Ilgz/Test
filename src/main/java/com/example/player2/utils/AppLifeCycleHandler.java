package com.example.player2.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class AppLifeCycleHandler implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static Context context;
    private static final String TAG = AppLifeCycleHandler.class.getSimpleName();
    private static boolean isInBackground = false;
    @Override
    public void onActivityCreated(@NonNull @NotNull Activity activity, @Nullable @org.jetbrains.annotations.Nullable Bundle bundle) {
    }

    @Override
    public void onActivityStarted(@NonNull @NotNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull @NotNull Activity activity) {
        if(isInBackground){

            System.out.println( "app went to foreground");
            isInBackground = false;
        }
    }

    @Override
    public void onActivityPaused(@NonNull @NotNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull @NotNull Activity activity) {


    }

    @Override
    public void onActivitySaveInstanceState(@NonNull @NotNull Activity activity, @NonNull @NotNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull @NotNull Activity activity) {

    }

    @Override
    public void onTrimMemory(int i) {
        if(i == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN){

            System.out.println( "app went to background");
            
            isInBackground = true;

        }
    }

    @Override
    public void onConfigurationChanged(@NonNull @NotNull Configuration configuration) {

    }

    @Override
    public void onLowMemory() {

    }

}