package kz.news.app.di.modules

import dagger.Module
import kz.news.app.di.modules.api.NewsApiModule

@Module(
    includes = [
        NewsApiModule::class
    ]
)
class ApiModule {
}