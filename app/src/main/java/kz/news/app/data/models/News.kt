package kz.news.app.data.models

data class News(
    val status: String?,
    val totalResults: String?,
    val articles: List<Article>?,
    var page: Int = 0
)
