package ru.sergjav.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import ru.sergjav.domain.BaseViewState
import ru.sergjav.domain.IBaseViewModel

@ExperimentalCoroutinesApi
abstract class BaseViewModel<ViewState : BaseViewState> : ViewModel(), IBaseViewModel<ViewState> {

    protected val viewStateChannel = BroadcastChannel<ViewState>(Channel.CONFLATED)

    private val job = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }
}