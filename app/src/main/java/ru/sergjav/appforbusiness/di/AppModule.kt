package ru.sergjav.appforbusiness.di

import org.koin.dsl.module
import ru.sergjav.appforbusiness.RouterImpl
import ru.sergjav.domain.Router
import ru.terrakok.cicerone.Cicerone

val appModule = module {
    single { Cicerone.create() }
    single { get<Cicerone<ru.terrakok.cicerone.Router>>().router }
    single { get<Cicerone<ru.terrakok.cicerone.Router>>().navigatorHolder }
    single<Router> { RouterImpl(get()) }
}