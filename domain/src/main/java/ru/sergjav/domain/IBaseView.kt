package ru.sergjav.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
interface IBaseView {

    val viewModel: IBaseViewModel<*>

    fun subscribeToViewModel()
}