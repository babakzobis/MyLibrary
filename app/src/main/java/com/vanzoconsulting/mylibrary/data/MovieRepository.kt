package com.vanzoconsulting.mylibrary.data

import com.vanzoconsulting.mylibrary.domain.entity.Movie
import com.vanzoconsulting.mylibrary.domain.MovieSearchResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun loadMovies(): Flow<DataResource<List<Movie>>>
    suspend fun searchMovie(movie: Movie): Flow<DataResource<MovieSearchResult>>
}