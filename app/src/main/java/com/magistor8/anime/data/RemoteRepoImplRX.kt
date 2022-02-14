package com.magistor8.anime.data

import com.magistor8.anime.data.retrofit.RemoteDataSource
import com.magistor8.anime.domain.RemoteRepository
import com.magistor8.anime.domain.entities.SearchDTO
import io.reactivex.rxjava3.core.Single


class RemoteRepoImplRX : RemoteRepository {

    private val remoteDataSource =  RemoteDataSource(true)

    override fun getSearchList(q: String): Single<SearchDTO> {
        //Запрашиваем данные
        return remoteDataSource.searchRX(q)
    }

}