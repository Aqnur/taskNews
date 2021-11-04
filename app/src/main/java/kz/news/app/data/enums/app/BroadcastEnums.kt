package kz.news.app.data.enums.app

import kz.news.app.BuildConfig

enum class BroadcastEnums(val id: String) {

    FOREGROUND_PUSH("${BuildConfig.APPLICATION_ID}.foreground_push")

}