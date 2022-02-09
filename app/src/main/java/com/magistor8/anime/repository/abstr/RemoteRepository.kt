package com.magistor8.anime.repository.abstr

import com.magistor8.anime.domain_model.SearchDTO
import retrofit2.Callback

interface RemoteRepository {
    fun getSearchList(query: String, callback: Callback<SearchDTO>)
}