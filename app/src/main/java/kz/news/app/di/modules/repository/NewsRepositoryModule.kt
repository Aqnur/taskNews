package kz.news.app.di.modules.repository

import dagger.Binds
import dagger.Module
import kz.news.app.data.repository.news.NewsRepository
import kz.news.app.data.repository.news.NewsRepositoryImpl
import javax.inject.Singleton

@Module
abstract class NewsRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

}