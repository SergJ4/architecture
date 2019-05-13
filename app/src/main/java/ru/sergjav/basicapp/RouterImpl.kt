package ru.sergjav.basicapp

import ru.sergjav.domain.BaseScreen
import ru.sergjav.domain.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class RouterImpl(private val router: ru.terrakok.cicerone.Router) : Router {
    override fun goTo(screen: BaseScreen) {
        router.navigateTo(screen.convertToScreen())
    }
}

private fun BaseScreen.convertToScreen(): SupportAppScreen {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
