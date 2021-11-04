package kz.news.app.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class DateTimeValue(
    val value: Date?,
    val date: String?,
    val time: String?,
    val ms: Long?
) : Parcelable