package com.vanzoconsulting.mylibrary.ui.movie

import androidx.lifecycle.viewModelScope
import com.vanzoconsulting.mylibrary.data.DataResource
import com.vanzoconsulting.mylibrary.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    StateViewModel<MoviesUiState>() {

    override var _uiState = MutableStateFlow(MoviesUiState(isLoading = true))
    override val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            movieRepository.loadMovies().collect { resource ->
                _uiState.update { currentUiState ->
                    currentUiState.copy(
                        isLoading = false,
                        value = (resource as? DataResource.Success)?.value ?: emptyList(),
                        userMessage = (resource as? DataResource.Failure)?.message
                    )
                }
            }
        }
    }

    fun reset() {
        _uiState.update {
            MoviesUiState(isLoading = true)
        }
        loadMovies()
    }
}