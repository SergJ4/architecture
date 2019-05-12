package ru.sergjav.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.get
import ru.sergjav.domain.IBaseViewModel

@ExperimentalCoroutinesApi
abstract class BaseFragment<ViewModel : IBaseViewModel<*>> : Fragment() {

    abstract val contentView: Int
    lateinit var viewModel: ViewModel

    protected val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        provideDependencies()
        super.onCreate(savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = View.inflate(context, contentView, container)

    abstract fun provideDependencies()

    protected inline fun <reified T : ViewModel> provideViewModel() {
        viewModel = get<T>()
    }

    protected fun showProgress(shouldShow: Boolean) {
        val swipeRefreshLayout = view?.findViewById<SwipeRefreshLayout?>(R.id.swipeRefresh)
        swipeRefreshLayout?.isRefreshing = shouldShow
    }
}