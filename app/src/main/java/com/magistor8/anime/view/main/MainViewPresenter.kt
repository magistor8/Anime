package com.magistor8.anime.view.main

import android.content.Context
import android.os.Bundle
import android.view.View
import com.magistor8.anime.domain_model.Data
import com.magistor8.anime.domain_model.getTestData
import com.magistor8.anime.view.SEARCH_RESULT
import com.magistor8.anime.view.SEARCH_RESULT_SHOW

class MainViewPresenter(val view: MainView) {

    var isSearchResult = false
    private var searchData: ArrayList<Data> = arrayListOf()

    fun performSearch(context: Context) {
        //Грузим тестовые данные
        searchData = getTestData(context)
        view.showSearchResult(searchData)
    }

    fun loadSavedState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null && savedInstanceState.getBoolean(SEARCH_RESULT_SHOW)) {
            isSearchResult = true
            searchData = savedInstanceState.getParcelableArrayList(SEARCH_RESULT)!!
            view.showSavedStateWithSearchResult(searchData)
        } else if (isSearchResult) {
            view.showSavedStateWithoutSearchResult()
        } else {
            view.show()
        }
    }

    fun putSearchData(outState: Bundle): Bundle {
        outState.putParcelableArrayList(SEARCH_RESULT, searchData)
        return outState
    }
}