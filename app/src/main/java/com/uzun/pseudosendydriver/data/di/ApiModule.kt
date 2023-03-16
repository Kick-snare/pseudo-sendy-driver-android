package com.uzun.pseudosendydriver.data.di

import com.uzun.pseudosendydriver.data.remote.api.MapsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideMapsApi(
        retrofit: Retrofit,
    ): MapsApi = retrofit.create(MapsApi::class.java)

}