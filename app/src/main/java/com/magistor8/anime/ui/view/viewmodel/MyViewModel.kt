package com.magistor8.anime.ui.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magistor8.anime.MyApp
import com.magistor8.anime.domain.RemoteRepository
import com.magistor8.anime.utils.Converter
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class MyViewModel : MainFragmentContract.MyViewModelInterface, ViewModel() {

    init {
        MyApp.instance.di.inject(this)
    }

    //Репозиторий
    @Inject
    lateinit var repository : RemoteRepository
    //Конвертер
    @Inject
    lateinit var converter : Converter

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
                    MainFragmentContract.ViewState.SuccesShortData(converter.convertSearchDTOtoShortData(it))
                )
            },
            onError = {
                viewState.mutable().postValue(MainFragmentContract.ViewState.Error(it))
            }
        )
    }

}