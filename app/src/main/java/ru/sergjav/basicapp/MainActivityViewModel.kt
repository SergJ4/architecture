package ru.sergjav.basicapp

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import ru.sergjav.core.BaseViewModel
import ru.sergjav.domain.BaseViewState
import ru.sergjav.domain.Router

@ExperimentalCoroutinesApi
class MainActivityViewModel(override val router: Router) :
    BaseViewModel<MainActivityViewState>() {

    override val viewState: ReceiveChannel<MainActivityViewState>
        get() = viewStateChannel.openSubscription()

    init {
        runBlocking {
            delay(2000)
            viewStateChannel.send(MainActivityViewState(false, true))
        }
    }
}

data class MainActivityViewState(
    override val showError: Boolean,
    override val showProgress: Boolean
) : BaseViewState(showProgress, showError)