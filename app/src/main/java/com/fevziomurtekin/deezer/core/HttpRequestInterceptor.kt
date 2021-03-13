package com.fevziomurtekin.deezer.core

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber


class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Timber.d("retrofit : $${chain.request()}" )
        return chain.proceed(chain.request())
    }
}
