package com.magistor8.anime

import android.app.Application
import com.magistor8.anime.utils.Converter

class MyApp: Application() {

    val converter = Converter()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }

}
