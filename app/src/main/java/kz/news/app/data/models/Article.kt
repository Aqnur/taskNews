package kz.news.app.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    val title: String?,
    val description: String?,
    val content: String?,
    val author: String?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("urlToImage") val urlToImage: String?
) : Parcelable
