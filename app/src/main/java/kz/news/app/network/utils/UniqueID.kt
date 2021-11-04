package kz.news.app.network.utils

import kz.news.app.data.preferences.Preferences
import java.util.*
import javax.inject.Inject

class UniqueID
@Inject constructor(
    private val preferences: Preferences
) {

    private var uniqueID: String? = null

    @Synchronized
    fun id(): String {
        if (uniqueID == null) {
            uniqueID = preferences.getUniqueID()
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString()
                preferences.setUniqueID(uniqueID)
            }
        }
        return uniqueID!!
    }

}