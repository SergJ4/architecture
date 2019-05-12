package ru.sergjav.appforbusiness.di

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sergjav.appforbusiness.MainActivityViewModel

@ExperimentalCoroutinesApi
val mainActivityModule = module {
    viewModel { MainActivityViewModel(get()) }
}