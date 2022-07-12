package com.magistor8.anime

import android.app.Application
import android.content.Context
import com.magistor8.anime.data.RemoteRepoImplRX
import com.magistor8.anime.data.retrofit.RemoteDataSource
import com.magistor8.anime.utils.Converter
import com.magistor8.anime.utils.Navigation

class MyApp: Application() {

    val converter = Converter()
    val navigation = Navigation()
    val repository = RemoteRepoImplRX(RemoteDataSource(true))

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }

}

val Context.app: MyApp
    get() = applicationContext as MyApp
