package com.magistor8.anime.repository.impl

import com.magistor8.anime.domain_model.SearchDTO
import com.magistor8.anime.repository.abstr.RemoteRepository
import retrofit2.Callback

class RemoteRepoImpl (
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {

    override fun getSearchList(q: String, callback: Callback<SearchDTO>){
        //Запрашиваем данные
        remoteDataSource.search(q, callback)
    }

}