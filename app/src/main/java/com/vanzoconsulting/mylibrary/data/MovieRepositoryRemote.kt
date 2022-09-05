package com.vanzoconsulting.mylibrary.data

import com.vanzoconsulting.mylibrary.domain.entity.Movie
import com.vanzoconsulting.mylibrary.network.MovieApi
import com.vanzoconsulting.mylibrary.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
/**
 * TODO: Replace the remote only nature of the repository with a local storage acting as caching
 * mechanism to reduce network I/O
 */
class MovieRepositoryRemote @Inject constructor(
    private val movieApi: MovieApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun loadMovies() = safeApiCall { movieApi.getMovies() }
    override suspend fun searchMovie(movie: Movie) =
        safeApiCall { movieApi.searchMovie(movie.title, movie.year) }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T) =
        flow<DataResource<T>> {
            emit(DataResource.Success(apiCall.invoke()))
        }.flowOn(ioDispatcher).catch { e ->
            when (e) {
                is HttpException -> {
                    emit(DataResource.Failure(e.response()?.errorBody().toString()))
                }
                else -> {
                    emit(DataResource.Failure(e.localizedMessage))
                }
            }
        }

}