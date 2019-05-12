package ru.sergjav.appforbusiness

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.sergjav.domain.IBaseView
import ru.sergjav.domain.IMainActivityViewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(), IBaseView {

    override val viewModel: IMainActivityViewModel
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun subscribeToViewModel() {

    }
}