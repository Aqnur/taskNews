package kz.news.app.ui.news.top_headlines

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kz.news.app.data.models.Article
import kz.news.app.data.models.News
import kz.news.app.data.repository.news.NewsRepository
import kz.news.app.ui_common.callbacks.RecyclerViewItemClickCallback
import kz.news.app.ui_common.callbacks.RetryCallback
import kz.news.app.utils.live_data.Event
import java.util.*
import javax.inject.Inject

class TopHeadlinesViewModel
@Inject constructor(
    private val app: Application,
    private val repository: NewsRepository
) : AndroidViewModel(app) {

    private var newsPage = 1
    private val getNews = MutableLiveData<Unit>()

    init {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                getNews.postValue(Unit)
            }
        }, 0, 5000)
    }

    fun setPage() {
        newsPage++
        getNews.postValue(Unit)
    }

    val topHeadlinesResource = Transformations.switchMap(getNews) {
        repository.getTopHeadlines("us", newsPage)
    }

    val status
        get() = Transformations.map(topHeadlinesResource) {
            it.status
        }

    val resource
        get() = Transformations.map(topHeadlinesResource) {
            it
        }

    val retryCallback = object : RetryCallback {
        override fun onRetryClick() {
            getNews.postValue(Unit)
        }
    }

    private val _list = MutableLiveData<Event<List<Article>>>()
    val list: LiveData<Event<List<Article>>>
        get() = _list

    var oldList: List<Article> ?= null

    fun onSuccess(news: News?) {
        val temp = mutableListOf<Article>()

        if(newsPage != 1) {
            oldList?.let {
                temp.addAll(it)
            }
        }

        news?.let {
            it.articles?.let {
                temp.addAll(it)
            }
        }

        oldList = temp
        oldList?.let {
            _list.postValue(Event(it))
        }
    }

    val recyclerViewItemClickCallback =
        object : RecyclerViewItemClickCallback {
            override fun onRecyclerViewItemClick(any: Any) {
                when (any) {
                    is Article -> {
                        _openDetails.postValue(Event(any))
                    }
                }
            }
        }

    private val _openDetails = MutableLiveData<Event<Article>>()
    val openDetails: LiveData<Event<Article>>
        get() = _openDetails
}