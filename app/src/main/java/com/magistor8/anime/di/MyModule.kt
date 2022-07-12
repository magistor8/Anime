package com.magistor8.anime.di

import com.magistor8.anime.data.RemoteRepoImplRX
import com.magistor8.anime.data.retrofit.RemoteDataSource
import com.magistor8.anime.domain.RemoteRepository
import com.magistor8.anime.utils.Converter
import com.magistor8.anime.utils.Navigation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyModule {


    @Singleton
    @Provides
    fun repo() : RemoteRepository {
        return RemoteRepoImplRX(RemoteDataSource(true))
    }

    @Singleton
    @Provides
    fun conv() : Converter {
        return Converter()
    }

    @Singleton
    @Provides
    fun nav() : Navigation {
        return Navigation()
    }

}