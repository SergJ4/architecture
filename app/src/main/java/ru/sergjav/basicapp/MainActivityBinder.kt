package ru.sergjav.basicapp

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.sergjav.domain.IViewBinder
import java.lang.ref.WeakReference

@ExperimentalCoroutinesApi
class MainActivityBinder(override val view: WeakReference<MainActivity>) :
    IViewBinder<MainActivity, MainActivityViewState> {

    override fun bind(state: MainActivityViewState) {
        Log.d("MainActivityBinder", state.toString())
    }
}