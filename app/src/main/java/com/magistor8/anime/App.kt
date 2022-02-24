package com.magistor8.anime

import android.app.Application
import android.content.Context
import com.magistor8.anime.di.DaggerMyComponent
import com.magistor8.anime.di.MyModule

class MyApp: Application() {

    val di by lazy {
        DaggerMyComponent.builder()
            .myModule(MyModule())
            .build()
    }

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
