package kz.news.app.ui.news.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kz.news.app.data.models.Article
import kz.news.app.data.repository.news.NewsRepository
import kz.news.app.utils.live_data.Event
import javax.inject.Inject

class NewsDetailsViewModel
@Inject constructor(
    private val app: Application,
    private val repository: NewsRepository
) : AndroidViewModel(app) {

    private lateinit var article: Article
    val articleData = MutableLiveData<Article>()

    fun setArgs(article: Article) {
        this.article = article
        articleData.postValue(article)
    }

    private val _toastMessage = MutableLiveData<Event<Unit>>()
    val toastMessage: LiveData<Event<Unit>>
        get() = _toastMessage

    fun favBtnClick() {
        _toastMessage.postValue(Event(Unit))
    }
}