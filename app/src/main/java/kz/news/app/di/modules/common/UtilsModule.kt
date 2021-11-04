package kz.news.app.di.modules.common

import dagger.Module
import dagger.Provides
import kz.news.app.data.preferences.Preferences
import kz.news.app.network.utils.HeaderUtils
import kz.news.app.network.utils.UniqueID
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideUniqueID(preferences: Preferences) = UniqueID(preferences)

    @Provides
    @Singleton
    fun provideHeaderUtils(preferences: Preferences, uniqueID: UniqueID) = HeaderUtils(preferences, uniqueID)

}