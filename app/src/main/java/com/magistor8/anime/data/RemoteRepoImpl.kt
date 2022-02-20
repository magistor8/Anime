package com.magistor8.anime.data

import com.magistor8.anime.data.retrofit.RemoteDataSource
import com.magistor8.anime.domain.SearchDTO
import com.magistor8.anime.domain.RemoteRepository
import retrofit2.Callback

class RemoteRepoImpl (
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {

    override fun getSearchList(q: String, callback: Callback<SearchDTO>){
        //Запрашиваем данные
        remoteDataSource.search(q, callback)
    }

}