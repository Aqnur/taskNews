package kz.news.app.di.contributes_android_injector.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.news.app.ui.news.details.NewsDetailsFragment
import kz.news.app.ui.news.everything.EverythingFragment
import kz.news.app.ui.news.top_headlines.TopHeadlinesFragment

@Module
abstract class NewsFragmentsBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun everythingFragment(): EverythingFragment

    @ContributesAndroidInjector
    internal abstract fun topHeadlinesFragment(): TopHeadlinesFragment

    @ContributesAndroidInjector
    internal abstract fun NewsDetailsFragment(): NewsDetailsFragment

}