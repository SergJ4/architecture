package ru.sergjav.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel

@ExperimentalCoroutinesApi
interface IBaseViewModel<ViewState : BaseViewState> {

    val router: Router

    val viewState: ReceiveChannel<ViewState>
}

open class BaseViewState(open val showProgress: Boolean, open val showError: Boolean)