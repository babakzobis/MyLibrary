package com.vanzoconsulting.mylibrary.data

import com.vanzoconsulting.mylibrary.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun loadMovies(): Flow<DataResource<List<Movie>>>
}