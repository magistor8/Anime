package com.magistor8.anime.domain

import io.reactivex.rxjava3.core.Single

interface RemoteRepositoryRX {
    fun getSearchList(query: String): Single<SearchDTO>
}