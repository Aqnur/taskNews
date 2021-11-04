package kz.news.app.data.repository.news

import androidx.lifecycle.LiveData
import kz.news.app.data.models.News
import kz.news.app.data.models.network.Resource

interface NewsRepository {

    fun getEverything(word: String, page: Int) : LiveData<Resource<News>>

    fun getTopHeadlines(country: String, page: Int) : LiveData<Resource<News>>

}