package ru.sergjav.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.get
import ru.sergjav.domain.IBaseViewModel

@ExperimentalCoroutinesApi
abstract class BaseActivity<ViewModel : IBaseViewModel<*>> : AppCompatActivity() {

    abstract val contentView: Int
    lateinit var viewModel: ViewModel

    protected val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        provideDependencies()
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        lifecycle.addObserver(scope)
    }

    abstract fun provideDependencies()

    protected inline fun <reified T : ViewModel> provideViewModel() {
        viewModel = get<T>()
    }
}