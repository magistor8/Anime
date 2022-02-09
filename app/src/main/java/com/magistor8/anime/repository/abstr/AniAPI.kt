package com.magistor8.anime.repository.abstr

import com.magistor8.anime.domain_model.AuthDTO
import com.magistor8.anime.domain_model.SearchDTO
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
}
