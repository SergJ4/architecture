package ru.sergjav.appforbusiness

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import ru.sergjav.core.BaseViewModel
import ru.sergjav.domain.BaseViewState
import ru.sergjav.domain.Router

@ExperimentalCoroutinesApi
class MainActivityViewModel(override val router: Router) :
    BaseViewModel<MainActivityViewState>() {

    private val viewStateChannel = BroadcastChannel<MainActivityViewState>(CONFLATED)

    override val viewState: ReceiveChannel<MainActivityViewState>
        get() = viewStateChannel.openSubscription()

    init {
        selectOnUi {

        }
    }
}

data class MainActivityViewState(
    override val showError: Boolean,
    override val showProgress: Boolean
) : BaseViewState(showProgress, showError)