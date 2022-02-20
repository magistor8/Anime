package com.magistor8.anime.ui.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magistor8.anime.MyApp
import com.magistor8.anime.domain.SearchDTO
import com.magistor8.anime.domain.ShortData
import com.magistor8.anime.data.retrofit.RemoteDataSource
import com.magistor8.anime.data.RemoteRepoImpl
import com.magistor8.anime.data.RemoteRepoRXImpl
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : MainFragmentContract.MyViewModel {

    //Репозиторий
    private val repository = RemoteRepoImpl(RemoteDataSource())
    private val repositoryRX = RemoteRepoRXImpl(RemoteDataSource(true))

    //Лайв дата
    override val viewState: LiveData<MainFragmentContract.ViewState> = MutableLiveData()
    override val actions: LiveData<MainFragmentContract.Action> = MutableLiveData()

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData<T>
    }

    //Ивенты
    override fun onEvent(event: MainFragmentContract.Event) {
        when (event) {
            is MainFragmentContract.Event.LoadData -> loadDataRX(event.q)
        }
    }

    //Грузим данные retrofit Call
    private fun loadData(q: String) {
        //Коллбэк ретрофит
        val searchCallback = object : Callback<SearchDTO> {
            override fun onResponse(call: Call<SearchDTO>, response: Response<SearchDTO>) {
                val shortData = MyApp.instance.converter.convertSearchDTOtoShortData(response.body())
                viewState.mutable().postValue(MainFragmentContract.ViewState.SuccesShortData(shortData))
            }

            override fun onFailure(call: Call<SearchDTO>, t: Throwable) {
                viewState.mutable().postValue(MainFragmentContract.ViewState.Error(t))
            }
        }
        //Запрос
        viewState.mutable().value = MainFragmentContract.ViewState.EmptyState
        Thread {
            repository.getSearchList(q, searchCallback)
        }.start()
    }

    //Грузим данные retrofit RX Single
    private fun loadDataRX(q: String) {
        viewState.mutable().value = MainFragmentContract.ViewState.EmptyState
        val single = repositoryRX.getSearchList(q).subscribeBy(
            onError = {
                viewState.mutable().postValue(MainFragmentContract.ViewState.Error(it))
            },
            onSuccess = {
                val shortData = MyApp.instance.converter.convertSearchDTOtoShortData(it)
                viewState.mutable().postValue(MainFragmentContract.ViewState.SuccesShortData(shortData))
            }
        )
    }

}