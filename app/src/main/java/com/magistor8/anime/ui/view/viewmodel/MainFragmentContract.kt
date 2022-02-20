package com.magistor8.anime.ui.view.viewmodel

import androidx.lifecycle.LiveData
import com.magistor8.anime.domain.entities.ShortData

interface MainFragmentContract {

    sealed interface ViewState {
        object EmptyState: ViewState
        data class SuccesShortData(val shortData: List<ShortData>) : ViewState
        data class Error(val error: Throwable): ViewState
    }
    sealed interface Action {
        object FistLaunch: Action
    }

    sealed interface Event {
        data class LoadData(val q : String): Event
    }

    interface MyViewModel {
        val viewState: LiveData<ViewState>
        val actions: LiveData<Action>

        fun onEvent(event: Event)
    }
}