package com.uzun.pseudosendy.data.di

import com.uzun.pseudosendydriver.data.repository.MapsRepository
import com.uzun.pseudosendydriver.data.repository.MapsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindMapsRepository(
        userRepository: MapsRepositoryImpl,
    ): MapsRepository

}