package com.magistor8.anime.repository.impl

import com.magistor8.anime.domain_model.AnimeData
import com.magistor8.anime.domain_model.ShortData
import com.magistor8.anime.domain_model.testShortData
import com.magistor8.anime.repository.abstr.RemoteRepository

class RemoteRepoImpl (
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {
    override fun getSearchList(query: String): List<ShortData> {
        //Тестовые данные
        return testShortData()
    }

    override fun getRandomAnime(): AnimeData {
        TODO("Not yet implemented")
    }
}