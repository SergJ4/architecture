package ru.sergjav.appforbusiness

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope
import ru.sergjav.appforbusiness.di.mainActivityModule
import ru.sergjav.core.BaseActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@ExperimentalCoroutinesApi
class MainActivity : BaseActivity<MainActivityViewModel>() {

    override val contentView: Int = R.layout.main_activity_layout

    val navigatorHolder: NavigatorHolder by inject()

    private val navigator = SupportAppNavigator(this, R.id.activity_container)

    override fun provideDependencies() {
        currentScope.beanRegistry.loadModules(listOf(mainActivityModule))
        provideViewModel<MainActivityViewModel>()
    }

    override fun onStart() {
        super.onStart()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
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