package kz.news.app.ui.news.everything

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kz.news.app.data.models.Article
import kz.news.app.data.models.News
import kz.news.app.data.repository.news.NewsRepository
import kz.news.app.ui_common.callbacks.RecyclerViewItemClickCallback
import kz.news.app.ui_common.callbacks.RetryCallback
import kz.news.app.ui_common.callbacks.SwipeToRefreshCallback
import kz.news.app.utils.live_data.Event
import javax.inject.Inject

class EverythingViewModel
@Inject constructor(
    private val app: Application,
    private val repository: NewsRepository
) : AndroidViewModel(app) {

    private var newsPage = 1
    private val getNews = MutableLiveData<Unit>()
    var isRefreshing = MutableLiveData<Boolean>()

    private val _list = MutableLiveData<Event<List<Article>>>()
    val list: LiveData<Event<List<Article>>>
        get() = _list

    private val _openDetails = MutableLiveData<Event<Article>>()
    val openDetails: LiveData<Event<Article>>
        get() = _openDetails

    init {
        refresh()
    }

    fun refresh() {
        newsPage = 1
        getNews.postValue(Unit)
        isRefreshing.value = false
    }

    fun setPage() {
        newsPage++
        getNews.postValue(Unit)
    }

    val everythingResource = Transformations.switchMap(getNews) {
        repository.getEverything("apple", newsPage)
    }

    val status
        get() = Transformations.map(everythingResource) {
            it.status
        }

    val resource
        get() = Transformations.map(everythingResource) {
            it
        }

    val retryCallback = object : RetryCallback {
        override fun onRetryClick() {
            refresh()
        }
    }

    val swipeToRefreshCallback = object :
        SwipeToRefreshCallback {
        override fun onSwipeToRefresh() {
            refresh()
            isRefreshing.value = false
        }
    }

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

}