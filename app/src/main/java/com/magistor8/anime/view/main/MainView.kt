package com.magistor8.anime.view.main

import com.magistor8.anime.domain_model.Data

interface MainView {
    fun showSearchResult(data: ArrayList<Data>)
    fun showSavedStateWithSearchResult(data: ArrayList<Data>)
    fun showSavedStateWithoutSearchResult()
    fun show()
}