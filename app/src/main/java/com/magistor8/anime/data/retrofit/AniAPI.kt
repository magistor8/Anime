package com.magistor8.anime.data.retrofit

import com.magistor8.anime.domain.AuthDTO
import com.magistor8.anime.domain.SearchDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*


interface AniAPI {
    @GET("auth/me")
    fun authMe(
        @Header("Authorization") key: String
    ): Call<AuthDTO>

    @GET("anime")
    fun search (
        @Query("title") q: String,
        @Query("nsfw") nsfw: String = "false"
    ) : Call<SearchDTO>

    @GET("anime")
    fun searchRX (
        @Query("title") q: String,
        @Query("nsfw") nsfw: String = "false"
    ) : Single<SearchDTO>
}
