package com.top.compose.sample.di

import com.top.compose.sample.data.repository.WanAndroidRepository
import com.top.compose.sample.data.repository.WanAndroidRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class InterfaceModule {

    @Binds
    abstract fun bindWanAndroidRepository(
        wanAndroidRepository: WanAndroidRepositoryImpl
    ): WanAndroidRepository
}