package kz.news.app.di.modules

import dagger.Module
import kz.news.app.di.modules.repository.NewsRepositoryModule

@Module(
    includes = [
        NewsRepositoryModule::class
    ]
)
class RepositoryModule {
}