package ru.sergjav.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.Copy
import org.kodein.di.Kodein
import org.kodein.di.android.kodein
import org.kodein.di.android.subKodein
import org.kodein.di.generic.instance
import ru.sergjav.domain.BaseView
import ru.sergjav.domain.BaseViewState
import ru.sergjav.domain.IBaseViewModel
import ru.sergjav.domain.IViewBinder

@ExperimentalCoroutinesApi
abstract class BaseActivity<VM : IBaseViewModel<*>> : AppCompatActivity(), BaseView {

    protected val subKodein by subKodein(kodein(), copy = Copy.All) {
        import(provideDependencies(), allowOverride = true)
    }

    abstract val contentView: Int
    lateinit var viewModel: VM
    val factory: ViewModelProvider.Factory by subKodein.instance()
    val viewBinder: IViewBinder<BaseActivity<*>, BaseViewState> by subKodein.instance()

    protected val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        provideDependencies()
        fetchViewModel()
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        lifecycle.addObserver(scope)
    }

    abstract fun provideDependencies(): Kodein.Module
    abstract fun fetchViewModel()

    protected inline fun <reified T : ViewModel> provideViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(T::class.java) as VM
    }
}