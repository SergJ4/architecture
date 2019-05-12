package ru.sergjav.core

import androidx.fragment.app.Fragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.sergjav.domain.IBaseView
import ru.sergjav.domain.IBaseViewModel

@ExperimentalCoroutinesApi
abstract class BaseFragment<ViewModel : IBaseViewModel<*>> : Fragment(), IBaseView {

    abstract override val viewModel: ViewModel
}