package com.magistor8.anime.contracts

import androidx.lifecycle.LiveData
import com.magistor8.anime.domain_model.ShortData

interface MainFragmentContract {

    sealed interface ViewState {
        object EmptyState: ViewState
        data class SuccesShortData(val shortData: List<ShortData>) : ViewState
    }
    sealed interface Action {
        object FistLaunch: Action
    }

    sealed interface Event {
        object LoadTestData: Event
    }

    interface MyViewModel {
        val viewState: LiveData<ViewState>
        val actions: LiveData<Action>

        fun onEvent(event: Event)
    }
}