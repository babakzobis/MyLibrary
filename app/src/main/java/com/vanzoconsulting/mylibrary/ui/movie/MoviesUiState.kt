package com.vanzoconsulting.mylibrary.ui.movie

import com.vanzoconsulting.mylibrary.domain.entity.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesUiState(
    override val value: List<Movie> = emptyList(),
    override val isLoading: Boolean = false,
    override val userMessage: String? = null,
): UiState<List<Movie>>() {
    override fun eraseUserMessage(): MoviesUiState = copy(value = value.toList(), userMessage = null)
}
