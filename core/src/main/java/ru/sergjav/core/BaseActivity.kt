package ru.sergjav.core

import android.app.Activity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.sergjav.domain.IBaseView
import ru.sergjav.domain.IBaseViewModel

@ExperimentalCoroutinesApi
abstract class BaseActivity<ViewModel : IBaseViewModel<*>> : Activity(), IBaseView {
    abstract override val viewModel: ViewModel
}