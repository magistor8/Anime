package com.magistor8.anime.ui.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magistor8.anime.MyApp
import com.magistor8.anime.data.RemoteRepoImplRX
import com.magistor8.anime.data.retrofit.RemoteDataSource
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyViewModel : MainFragmentContract.MyViewModel {

    //Репозиторий
    private val repository = MyApp.instance.repository

    //Лайв дата
    override val viewState: LiveData<MainFragmentContract.ViewState> = MutableLiveData()
    override val actions: LiveData<MainFragmentContract.Action> = MutableLiveData()

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData<T>
    }

    //Ивенты
    override fun onEvent(event: MainFragmentContract.Event) {
        when (event) {
            is MainFragmentContract.Event.LoadData -> loadData(event.q)
        }
    }

    //Грузим данные
    private fun loadData(q: String) {
        viewState.mutable().postValue(MainFragmentContract.ViewState.EmptyState)
        repository.getSearchList(q).subscribeBy(
            onSuccess = {
                viewState.mutable().postValue(
                    MainFragmentContract.ViewState.SuccesShortData(MyApp.instance.converter.convertSearchDTOtoShortData(it))
                )
            },
            onError = {
                viewState.mutable().postValue(MainFragmentContract.ViewState.Error(it))
            }
        )
    }

}