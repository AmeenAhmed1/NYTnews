package com.ameen.nytnews.di

import com.ameen.nytnews.viewmodel.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(target: HomeViewModel)
}