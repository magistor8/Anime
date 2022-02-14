package com.magistor8.anime.data.retrofit

import com.google.gson.GsonBuilder
import com.magistor8.anime.BuildConfig
import com.magistor8.anime.domain.entities.AuthDTO
import com.magistor8.anime.domain.entities.SearchDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource(rx: Boolean = false) {

    private val api = Retrofit.Builder()
        .baseUrl("https://api.aniapi.com/v1/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .apply { if (rx) this.addCallAdapterFactory(RxJava3CallAdapterFactory.create()) }
        .build().create(AniAPI::class.java)

    fun getAuth(callback: Callback<AuthDTO>) {
        api.authMe(BuildConfig.ANIAPI_API_KEY).enqueue(callback)
    }

    fun search(q: String): Call<SearchDTO> {
        return api.search(q)
    }

    fun searchRX(q: String) : Single<SearchDTO>{
        return api.searchRX(q)
    }

}