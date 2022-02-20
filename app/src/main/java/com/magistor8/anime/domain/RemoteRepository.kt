package com.magistor8.anime.domain

import retrofit2.Callback

interface RemoteRepository {
    fun getSearchList(query: String, callback: Callback<SearchDTO>)
}