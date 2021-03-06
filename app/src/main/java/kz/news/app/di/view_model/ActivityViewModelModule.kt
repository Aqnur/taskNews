package kz.news.app.di.view_model

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kz.news.app.di.view_model.base.ViewModelKey
import kz.news.app.ui.activities.main.MainViewModel

@Suppress("unused")
@Module
abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindActivityMainViewModel(viewModel: MainViewModel): ViewModel

}