package ru.sergjav.appforbusiness

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import org.koin.androidx.scope.currentScope
import ru.sergjav.appforbusiness.di.mainActivityModule
import ru.sergjav.core.BaseActivity

@ExperimentalCoroutinesApi
class MainActivity : BaseActivity<MainActivityViewModel>() {

    override val contentView: Int = R.layout.main_activity_layout

    override fun provideDependencies() {
        currentScope.beanRegistry.loadModules(listOf(mainActivityModule))
        provideViewModel<MainActivityViewModel>()
    }

    fun subscribeToViewModel() {
        scope.launch {
            val state = select<MainActivityViewState> { viewModel.viewState }
            bindState(state)
        }
    }

    private fun bindState(state: MainActivityViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}