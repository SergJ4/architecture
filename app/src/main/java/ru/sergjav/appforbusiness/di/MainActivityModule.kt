package ru.sergjav.appforbusiness.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ru.sergjav.appforbusiness.ACTIVITY_CONTEXT
import ru.sergjav.appforbusiness.MainActivity
import ru.sergjav.appforbusiness.MainActivityBinder
import ru.sergjav.appforbusiness.MainActivityViewModel
import ru.sergjav.core.BaseActivity
import ru.sergjav.core.KodeinViewModelFactory
import ru.sergjav.domain.BaseViewState
import ru.sergjav.domain.IViewBinder
import java.lang.ref.WeakReference

@ExperimentalCoroutinesApi
fun mainActivityModule(activity: MainActivity, kodein: Kodein) =
    Kodein.Module(name = "MainActivityModule") {

        bind<Context>(tag = ACTIVITY_CONTEXT) with singleton { activity }
        bind<ViewModelProvider.Factory>() with singleton { KodeinViewModelFactory(kodein) }
        bind<MainActivityViewModel>() with singleton { MainActivityViewModel(instance()) }

        bind<IViewBinder<BaseActivity<*>, BaseViewState>>() with instance(
            MainActivityBinder(WeakReference(activity))
                    as IViewBinder<BaseActivity<*>, BaseViewState>
        )
    }