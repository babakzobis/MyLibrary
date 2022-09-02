package com.vanzoconsulting.mylibrary.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanzoconsulting.mylibrary.data.DataResource
import com.vanzoconsulting.mylibrary.data.MovieRepository
import com.vanzoconsulting.mylibrary.domain.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>(emptyList())
    val movies: LiveData<List<Movie>>
        get() = _movies

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            val resource = movieRepository.loadMovies()
            if (resource is DataResource.Success) {
                _movies.postValue(resource.value)
            } else if (resource is DataResource.Failure) {
                // Todo: emit an error event for displaying an error message
                _movies.postValue(emptyList())
            }
        }
    }
}