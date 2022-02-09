package com.magistor8.anime.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magistor8.anime.MyApp
import com.magistor8.anime.contracts.MainFragmentContract
import com.magistor8.anime.domain_model.SearchDTO
import com.magistor8.anime.domain_model.ShortData
import com.magistor8.anime.repository.impl.RemoteDataSource
import com.magistor8.anime.repository.impl.RemoteRepoImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : MainFragmentContract.MyViewModel {

    //Репозиторий
    private val repository = RemoteRepoImpl(RemoteDataSource())

    //Лайв дата
    override val viewState: LiveData<MainFragmentContract.ViewState> = MutableLiveData()
    override val actions: LiveData<MainFragmentContract.Action> = MutableLiveData()

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData<T>
    }

    //Коллбэки ретрофит
    private val searchCallback = object : Callback<SearchDTO> {
        override fun onResponse(call: Call<SearchDTO>, response: Response<SearchDTO>) {
            val shortData = MyApp.instance.converter.convertSearchDTOtoShortData(response.body())
            viewState.mutable().postValue(MainFragmentContract.ViewState.SuccesShortData(shortData))
        }

        override fun onFailure(call: Call<SearchDTO>, t: Throwable) {
            viewState.mutable().postValue(MainFragmentContract.ViewState.Error(t))
        }
    }

    //Ивенты
    override fun onEvent(event: MainFragmentContract.Event) {
        when (event) {
            is MainFragmentContract.Event.LoadData -> loadData(event.q)
        }
    }

    //Грузим тестовые данные
    private fun loadData(q: String) {
        var data: List<ShortData>
        viewState.mutable().value = MainFragmentContract.ViewState.EmptyState
        Thread {
            repository.getSearchList(q, searchCallback)
        }.start()
    }

}