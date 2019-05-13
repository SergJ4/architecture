package ru.sergjav.domain

import java.lang.ref.WeakReference

interface IViewBinder<View : BaseView, ViewState : BaseViewState> {

    val view: WeakReference<View>

    fun bind(state: ViewState)
}