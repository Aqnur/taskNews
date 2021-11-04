package kz.news.app.di.modules

import dagger.Module
import kz.news.app.di.modules.common.NetworkModule
import kz.news.app.di.modules.common.PreferenceModule
import kz.news.app.di.modules.common.UtilsModule
import kz.news.app.di.view_model.base.ViewModelFactoryModule

@Module(
    includes = [
        NetworkModule::class,
        PreferenceModule::class,
        UtilsModule::class,

        //api
        ApiModule::class,

        //repository
        RepositoryModule::class,

        //
        ViewModelFactoryModule::class,
        ViewModelModule::class
    ]
)
class AppModule {
}