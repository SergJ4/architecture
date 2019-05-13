package ru.sergjav.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.android.kodein
import org.kodein.di.android.subKodein
import org.kodein.di.generic.instance
import ru.sergjav.domain.BaseView
import ru.sergjav.domain.IBaseViewModel

@ExperimentalCoroutinesApi
abstract class BaseFragment<VM : IBaseViewModel<*>> : Fragment(), BaseView {

    protected val subKodein by subKodein(kodein(context = context!!), copy = Copy.All) {
        import(provideDependencies(), allowOverride = true)
    }

    abstract val contentView: Int
    lateinit var viewModel: VM
    val factory: ViewModelProvider.Factory by subKodein.instance()

    protected val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        provideDependencies()
        fetchViewModel()
        super.onCreate(savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = View.inflate(context, contentView, container)

    abstract fun provideDependencies(): Kodein.Module
    abstract fun fetchViewModel()

    protected inline fun <reified T : ViewModel> provideViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(T::class.java) as VM
    }

    protected fun showProgress(shouldShow: Boolean) {
        val swipeRefreshLayout = view?.findViewById<SwipeRefreshLayout?>(R.id.swipeRefresh)
        swipeRefreshLayout?.isRefreshing = shouldShow
    }
}