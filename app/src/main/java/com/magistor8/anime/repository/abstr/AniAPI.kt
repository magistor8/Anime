package com.magistor8.anime.repository.abstr

import com.magistor8.anime.domain_model.AuthDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface AniAPI {
    @GET("auth/me")
    fun authMe(
        @Header("Authorization") key: String
    ): Call<AuthDTO>
}
