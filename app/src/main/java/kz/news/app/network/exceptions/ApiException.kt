package kz.news.app.network.exceptions

import kz.news.app.data.models.network.error.ApiError

class ApiException(val apiError: ApiError) : Exception()