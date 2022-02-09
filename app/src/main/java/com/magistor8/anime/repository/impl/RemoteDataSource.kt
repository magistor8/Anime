package com.magistor8.anime.repository.impl

import com.google.gson.GsonBuilder
import com.magistor8.anime.BuildConfig
import com.magistor8.anime.domain_model.AuthDTO
import com.magistor8.anime.repository.abstr.AniAPI
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private val api = Retrofit.Builder()
        .baseUrl("https://api.aniapi.com/v1/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(AniAPI::class.java)

    fun getAuth(callback: Callback<AuthDTO>) {
        api.authMe(BuildConfig.ANIAPI_API_KEY).enqueue(callback)
    }

}