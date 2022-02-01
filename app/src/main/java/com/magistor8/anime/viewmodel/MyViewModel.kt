package com.magistor8.anime.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magistor8.anime.contracts.MainFragmentContract
import com.magistor8.anime.domain_model.ShortData
import com.magistor8.anime.repository.impl.RemoteDataSource
import com.magistor8.anime.repository.impl.RemoteRepoImpl

class MyViewModel : MainFragmentContract.MyViewModel {

    //Репозиторий
    private val repository = RemoteRepoImpl(RemoteDataSource())

    //Данные
    override val viewState: LiveData<MainFragmentContract.ViewState> = MutableLiveData()
    override val actions: LiveData<MainFragmentContract.Action> = MutableLiveData()

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as MutableLiveData<T>
    }

    //Ивенты
    override fun onEvent(event: MainFragmentContract.Event) {
        when (event) {
            is MainFragmentContract.Event.LoadTestData -> loadTestData()
        }
    }

    //Грузим тестовые данные
    private fun loadTestData() {
        var data: List<ShortData>
        viewState.mutable().value = MainFragmentContract.ViewState.EmptyState
        Thread {
            //Эмитируем загрузку
            Thread.sleep(3000)
            data = repository.getSearchList("")
            viewState.mutable().postValue(MainFragmentContract.ViewState.SuccesShortData(data))
        }.start()
    }

}