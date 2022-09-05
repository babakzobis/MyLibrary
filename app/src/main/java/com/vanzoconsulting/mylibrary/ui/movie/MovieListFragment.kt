package com.vanzoconsulting.mylibrary.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.vanzoconsulting.mylibrary.R
import com.vanzoconsulting.mylibrary.databinding.FragmentMovieListBinding
import com.vanzoconsulting.mylibrary.ui.adapters.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : BindingViewModelFragment<FragmentMovieListBinding, MovieListViewModel, MoviesUiState>(
    FragmentMovieListBinding::inflate
) {

    override val viewModel: MovieListViewModel by viewModels()
    private val movieListAdapter = MovieListAdapter()
    override val dismissActionLabel = R.string.fragment_movie_list_retry
    override val dismissAction: () -> Unit
        get() = viewModel::reset

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentMovieListMain.adapter = movieListAdapter
    }

    override fun showDismissAction() = !binding.isLoading && !binding.hasMovies

    override fun updateUI(uiState: MoviesUiState) {
        binding.isLoading = uiState.isLoading
        binding.hasMovies = uiState.value.isNotEmpty()
        movieListAdapter.submitList(uiState.value)
        uiState.userMessage?.let {
            showUserMessage(it)
        }
    }
}