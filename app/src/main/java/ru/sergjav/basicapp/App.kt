package ru.sergjav.basicapp

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import ru.sergjav.basicapp.di.appModule

const val APP_CONTEXT = "app_context"

class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule(this@App))
    }
}