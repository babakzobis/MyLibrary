package com.vanzoconsulting.mylibrary.data.remote

import com.vanzoconsulting.mylibrary.domain.entity.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    companion object {
        var ENDPOINT = "https://www.digitalia.be"
    }

    @GET("/coding_test/movies.json")
    suspend fun getMovies(): List<Movie>
}