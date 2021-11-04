package kz.news.app.di.modules.api

import dagger.Module
import dagger.Provides
import kz.news.app.network.api.NewsApi
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class NewsApiModule {

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

}