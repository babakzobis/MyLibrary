package com.vanzoconsulting.mylibrary.network

import com.vanzoconsulting.mylibrary.domain.entity.Movie
import com.vanzoconsulting.mylibrary.domain.MovieSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    companion object {
        const val ENDPOINT_LIST = "https://www.digitalia.be"
        const val ENDPOINT_DETAILS = "https://api.themoviedb.org"
    }

    @GET("$ENDPOINT_LIST/coding_test/movies.json")
    suspend fun getMovies(): List<Movie>

    @GET("$ENDPOINT_DETAILS/3/search/movie?page=1&include_adult=false")
    suspend fun searchMovie(@Query("query") query: String?, @Query("year") year: Int?): MovieSearchResult
}