package com.magistor8.anime.repository.abstr

import com.magistor8.anime.domain_model.AnimeData
import com.magistor8.anime.domain_model.ShortData

interface RemoteRepository {
    fun getSearchList(query: String) : List<ShortData>
    fun getRandomAnime() : AnimeData
}