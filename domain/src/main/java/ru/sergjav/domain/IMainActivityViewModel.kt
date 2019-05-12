package ru.sergjav.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
interface IMainActivityViewModel : IBaseViewModel<MainActivityViewState>

data class MainActivityViewState(
    override val showProgress: Boolean,
    override val showError: Boolean
) : BaseViewState(showProgress, showError)