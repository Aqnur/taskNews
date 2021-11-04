package kz.news.app.di.modules

import dagger.Module
import kz.news.app.di.view_model.NewsViewModelModule
import kz.news.app.di.view_model.ActivityViewModelModule

@Module(
    includes = [
        ActivityViewModelModule::class,
        NewsViewModelModule::class
    ]
)
class ViewModelModule {
}