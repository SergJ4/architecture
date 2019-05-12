package ru.sergjav.domain

interface Router {

    fun goTo(screen: BaseScreen)
}

sealed class Screen
open class BaseScreen : Screen()