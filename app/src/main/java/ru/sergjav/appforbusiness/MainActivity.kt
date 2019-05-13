package ru.sergjav.appforbusiness

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import ru.sergjav.appforbusiness.di.mainActivityModule
import ru.sergjav.core.BaseActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator

const val ACTIVITY_CONTEXT = "activity_context"

@ExperimentalCoroutinesApi
class MainActivity : BaseActivity<MainActivityViewModel>() {

    override val contentView: Int = R.layout.main_activity_layout

    val navigatorHolder: NavigatorHolder by subKodein.instance()

    private val navigator = SupportAppNavigator(this, R.id.activity_container)

    override fun provideDependencies() = mainActivityModule(this, subKodein)

    override fun fetchViewModel() {
        provideViewModel<MainActivityViewModel>()
    }

    override fun onResume() {
        super.onResume()
        subscribeToViewModel()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    private fun subscribeToViewModel() {
        scope.launch {
            for (state in viewModel.viewState) {
                viewBinder.bind(state)
            }
        }
    }
}