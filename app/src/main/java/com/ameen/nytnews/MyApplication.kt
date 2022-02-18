package com.ameen.nytnews

import android.app.Application
import com.ameen.nytnews.di.AppComponent
import com.ameen.nytnews.di.DaggerAppComponent
import com.ameen.nytnews.di.NetworkModule

class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .build()
    }
}