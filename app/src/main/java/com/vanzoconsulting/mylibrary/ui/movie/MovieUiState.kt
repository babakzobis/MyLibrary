package com.vanzoconsulting.mylibrary.ui.movie

import com.vanzoconsulting.mylibrary.domain.entity.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUiState(
    override val value: Movie? = null,
    override val isLoading: Boolean = false,
    override val userMessage: String? = null
): UiState<Movie>() {
    override fun eraseUserMessage(): MovieUiState = copy(userMessage = null)
}
