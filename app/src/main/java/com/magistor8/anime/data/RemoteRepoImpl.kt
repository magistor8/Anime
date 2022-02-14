package com.magistor8.anime.data

import com.magistor8.anime.data.retrofit.RemoteDataSource
import com.magistor8.anime.domain.RemoteRepository
import com.magistor8.anime.domain.entities.SearchDTO
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.SingleSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteRepoImpl: RemoteRepository {

    private val remoteDataSource = RemoteDataSource()

    override fun getSearchList(q: String): Single<SearchDTO> {
        //Запрашиваем данные
        return remoteDataSource.search(q).toSingle()
    }

    fun <T> Call<T>.toSingle(): Single<T> {
        val single = SingleSubject.create<T>()
        this.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                single.onError(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body().let {
                    single.onSuccess(response.body()!!)
                }
            }
        })
        return single
    }

}