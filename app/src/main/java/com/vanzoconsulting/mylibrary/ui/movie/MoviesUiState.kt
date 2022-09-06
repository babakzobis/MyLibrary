package com.vanzoconsulting.mylibrary.ui.movie

import android.os.Parcelable
import com.vanzoconsulting.mylibrary.domain.entity.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: String? = null,
): Parcelable
