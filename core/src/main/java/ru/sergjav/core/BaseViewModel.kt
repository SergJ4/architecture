package ru.sergjav.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import ru.sergjav.domain.BaseViewState
import ru.sergjav.domain.IBaseViewModel

@ExperimentalCoroutinesApi
abstract class BaseViewModel<ViewState : BaseViewState> : ViewModel(), IBaseViewModel<ViewState> {

    private val viewStateChannel = BroadcastChannel<ViewState>(CONFLATED)

    override val viewState: ReceiveChannel<ViewState>
        get() = viewStateChannel.openSubscription()
}