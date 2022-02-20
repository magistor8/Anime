package com.magistor8.anime.ui.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magistor8.anime.MyApp
import com.magistor8.anime.data.RemoteRepoImplRX
import io.reactivex.rxjava3.kotlin.subscribeBy

class MyViewModel : MainFragmentContract.MyViewModel {

    //Репозиторий
    private val repository = RemoteRepoImplRX()

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
        viewState.mutable().value = MainFragmentContract.ViewState.EmptyState
        repository.getSearchList(q).subscribeBy(
            onSuccess = {
                viewState.mutable().value =
                    MainFragmentContract.ViewState.SuccesShortData(MyApp.instance.converter.convertSearchDTOtoShortData(it))
            },
            onError = {
                viewState.mutable().value = MainFragmentContract.ViewState.Error(it)
            }
        )
    }

}