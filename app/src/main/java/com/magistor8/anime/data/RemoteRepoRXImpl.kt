package com.magistor8.anime.data

import com.magistor8.anime.data.retrofit.RemoteDataSource
import com.magistor8.anime.domain.SearchDTO
import com.magistor8.anime.domain.RemoteRepository
import com.magistor8.anime.domain.RemoteRepositoryRX
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import retrofit2.Callback

class RemoteRepoRXImpl (
    private val remoteDataSource: RemoteDataSource
) : RemoteRepositoryRX {

    override fun getSearchList(q: String) : Single<SearchDTO>{
        //Запрашиваем данные
        return remoteDataSource.searchRX(q)
    }

}