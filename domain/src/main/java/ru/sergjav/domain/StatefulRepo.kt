package ru.sergjav.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel

@ExperimentalCoroutinesApi
abstract class StatefulRepo<Data> {

    private val repoStateChannel = BroadcastChannel<RepoState>(CONFLATED)
    private val repoDataChannel = BroadcastChannel<Data>(CONFLATED)

    val repoState: ReceiveChannel<RepoState>
        get() = repoStateChannel.openSubscription()

    val repoData: ReceiveChannel<Data>
        get() = repoDataChannel.openSubscription()
}

sealed class RepoState
object Success : RepoState()
data class Failure(val throwable: Throwable) : RepoState()
object Loading : RepoState()