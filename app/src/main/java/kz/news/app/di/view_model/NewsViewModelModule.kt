package kz.news.app.di.view_model

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kz.news.app.di.view_model.base.ViewModelKey
import kz.news.app.ui.news.details.NewsDetailsViewModel
import kz.news.app.ui.news.everything.EverythingViewModel
import kz.news.app.ui.news.top_headlines.TopHeadlinesViewModel

@Suppress("unused")
@Module
abstract class NewsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EverythingViewModel::class)
    abstract fun bindEverythingViewModel(viewModel: EverythingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopHeadlinesViewModel::class)
    abstract fun bindTopHeadlinesViewModel(viewModel: TopHeadlinesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailsViewModel::class)
    abstract fun bindNewsDetailsViewModel(viewModel: NewsDetailsViewModel): ViewModel

}