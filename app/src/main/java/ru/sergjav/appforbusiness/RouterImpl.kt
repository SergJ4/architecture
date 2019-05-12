package ru.sergjav.appforbusiness

import ru.sergjav.domain.BaseScreen
import ru.sergjav.domain.Router
import ru.terrakok.cicerone.Screen

class RouterImpl(private val router: ru.terrakok.cicerone.Router) : Router {
    override fun goTo(screen: BaseScreen) {
        router.navigateTo(screen.convertToScreen())
    }
}

private fun BaseScreen.convertToScreen(): Screen {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
