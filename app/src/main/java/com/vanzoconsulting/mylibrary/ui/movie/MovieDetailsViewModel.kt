package com.vanzoconsulting.mylibrary.ui.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanzoconsulting.mylibrary.data.DataResource
import com.vanzoconsulting.mylibrary.data.MovieRepository
import com.vanzoconsulting.mylibrary.domain.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * Todo: share the fully hydrated movie object with MovieListViewModel
 */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : StateViewModel<MovieUiState>() {

    private var movie = savedStateHandle.get<Movie>(SAVED_STATE_KEY_MOVIE)!!
    override var _uiState = MutableStateFlow(MovieUiState(isLoading = true, value = movie))
    override val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    init {
        loadMovie()
    }

    private fun loadMovie() {
        viewModelScope.launch {
            movieRepository.searchMovie(movie).collect { resource ->
                _uiState.update { currentUiState ->
                    currentUiState.copy(
                        isLoading = false,
                        value = (resource as? DataResource.Success)?.value?.results?.find {
                            it.title == movie.title && it.safeYear == movie.safeYear
                        }?.let {
                            movie.copy(
                                id = it.id,
                                overview = it.overview,
                                releaseDate = it.releaseDate,
                                backdropUrl = it.backdropUrl,
                                averageVote = it.averageVote
                            )
                        },
                        userMessage = (resource as? DataResource.Failure)?.message
                    )
                }
            }
        }
    }

    companion object {
        private const val SAVED_STATE_KEY_MOVIE = "movie"
    }
}