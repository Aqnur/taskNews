package kz.news.app.di.contributes_android_injector.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.news.app.di.contributes_android_injector.fragment.NewsFragmentsBuildersModule
import kz.news.app.ui.activities.main.MainActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            NewsFragmentsBuildersModule::class
        ]
    )
    internal abstract fun mainActivity(): MainActivity

}