package com.magistor8.anime.di

import com.magistor8.anime.ui.view.MainActivity
import com.magistor8.anime.ui.view.viewmodel.MyViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MyModule::class])
interface MyComponent {
    fun inject(myActivity: MainActivity)
    fun inject(viewModel: MyViewModel)
}