package com.vanzoconsulting.mylibrary.data.remote

import com.vanzoconsulting.mylibrary.data.DataResource
import com.vanzoconsulting.mylibrary.data.MovieRepository
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryRemote @Inject constructor(private val movieApi: MovieApi) : MovieRepository {

    override suspend fun loadMovies() = safeApiCall { movieApi.getMovies() }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T) = try {
        DataResource.Success(apiCall.invoke())
    } catch (e: Exception) {
        when (e) {
            is HttpException -> {
                DataResource.Failure(e.response()?.errorBody().toString())
            }
            else -> {
                DataResource.Failure()
            }
        }
    }
}