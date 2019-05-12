package ru.sergjav.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.selects.select
import ru.sergjav.domain.BaseViewState
import ru.sergjav.domain.IBaseViewModel

@ExperimentalCoroutinesApi
abstract class BaseViewModel<ViewState : BaseViewState> : ViewModel(), IBaseViewModel<ViewState> {

    private val viewStateChannel = BroadcastChannel<ViewState>(Channel.CONFLATED)

    private val job = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }

    protected fun selectOnUi(action: (ViewState) -> Unit) {
        uiScope.launch {
            select<Unit> {
                viewStateChannel.openSubscription().onReceive {
                    action(it)
                }
            }
        }
    }
}