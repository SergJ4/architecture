package ru.sergjav.appforbusiness

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import ru.sergjav.domain.IMainActivityViewModel
import ru.sergjav.domain.MainActivityViewState
import ru.sergjav.domain.Router

@ExperimentalCoroutinesApi
class MainActivityViewModel(override val router: Router) : ViewModel(), IMainActivityViewModel {

    private val viewStateChannel = BroadcastChannel<MainActivityViewState>(CONFLATED)

    override val viewState: ReceiveChannel<MainActivityViewState>
        get() = viewStateChannel.openSubscription()

    init {

    }
}