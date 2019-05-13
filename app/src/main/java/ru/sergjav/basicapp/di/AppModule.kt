package ru.sergjav.basicapp.di

import android.app.Application
import android.content.Context
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import ru.sergjav.basicapp.APP_CONTEXT
import ru.sergjav.basicapp.RouterImpl
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

fun appModule(app: Application) = Kodein.Module(name = "AppModule") {
    bind<Context>(tag = APP_CONTEXT) with singleton { app.applicationContext }
    bind<Cicerone<Router>>() with singleton { Cicerone.create() }
    bind<Router>() with singleton { instance<Cicerone<Router>>().router }
    bind<NavigatorHolder>() with singleton { instance<Cicerone<Router>>().navigatorHolder }
    bind<ru.sergjav.domain.Router>() with singleton { RouterImpl(instance()) }
}