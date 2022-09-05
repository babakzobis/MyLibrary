package com.vanzoconsulting.mylibrary.network

import com.vanzoconsulting.mylibrary.BuildConfig
import com.vanzoconsulting.mylibrary.network.MovieApi.Companion.ENDPOINT_DETAILS
import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val url = originalRequest.url.takeIf { "${it.scheme}://${it.host}" == ENDPOINT_DETAILS }?.newBuilder()
      ?.addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
      ?.build()

    return chain.proceed(originalRequest.newBuilder().url(url ?: originalRequest.url).build())
  }
}
