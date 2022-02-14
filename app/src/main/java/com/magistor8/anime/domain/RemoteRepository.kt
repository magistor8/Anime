package com.magistor8.anime.domain

import com.magistor8.anime.domain.entities.SearchDTO
import io.reactivex.rxjava3.core.Single

interface RemoteRepository {
    fun getSearchList(query: String): Single<SearchDTO>
}