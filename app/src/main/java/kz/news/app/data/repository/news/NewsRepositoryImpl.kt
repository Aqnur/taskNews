package kz.news.app.data.repository.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kz.news.app.data.consts.ApiKey
import kz.news.app.data.models.News
import kz.news.app.data.models.network.Resource
import kz.news.app.data.repository.error_handler.RepositoryErrorHandler
import kz.news.app.network.api.NewsApi
import javax.inject.Inject

class NewsRepositoryImpl
@Inject constructor(
    private val errorHandler: RepositoryErrorHandler,
    private val api: NewsApi
) : NewsRepository {

    override fun getEverything(word: String, page: Int): LiveData<Resource<News>> {
        return liveData {
            try {
                emit(
                    Resource.loading(null)
                )

                val request = api.getEverything(word, 15, page, ApiKey.API_KEY)
                with(request) {
                    if (isSuccessful) {
                        emit(
                            Resource.success(body())
                        )
                    } else {
                        emit(
                            errorHandler.getError(code(), errorBody())
                        )
                    }
                }
            } catch (e: Exception) {
                emit(
                    errorHandler.getError<News>(e)
                )
            }
        }
    }

    override fun getTopHeadlines(country: String, page: Int): LiveData<Resource<News>> {
        return liveData {
            try {
                emit(
                    Resource.loading(null)
                )

                val request = api.getTopHeadlines(country,15, page, ApiKey.API_KEY)
                with(request) {
                    if (isSuccessful) {
                        emit(
                            Resource.success(body())
                        )
                    } else {
                        emit(
                            errorHandler.getError(code(), errorBody())
                        )
                    }
                }
            } catch (e: Exception) {
                emit(
                    errorHandler.getError<News>(e)
                )
            }
        }
    }

}