package com.vanzoconsulting.mylibrary.data

import com.vanzoconsulting.mylibrary.domain.entity.Movie

interface MovieRepository {
    suspend fun loadMovies(): DataResource<List<Movie>>
}