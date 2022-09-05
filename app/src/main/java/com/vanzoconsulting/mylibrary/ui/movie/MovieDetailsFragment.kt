package com.vanzoconsulting.mylibrary.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vanzoconsulting.mylibrary.R
import com.vanzoconsulting.mylibrary.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment :
    BindingViewModelFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel, MovieUiState>(
        FragmentMovieDetailsBinding::inflate
    ) {

    override val viewModel: MovieDetailsViewModel by viewModels()
    override val dismissActionLabel = R.string.fragment_movie_details_message_ok
    override val dismissAction: () -> Unit
        get() = { findNavController().navigateUp() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieDetailToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.movieDetailToolbar.navigationIcon
        binding.movieDetailToolbar.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun updateUI(uiState: MovieUiState) {
        binding.isLoading = uiState.isLoading
        binding.movie = uiState.value
        uiState.userMessage?.let {
            showUserMessage(it)
        }
    }

    override fun showDismissAction() = !binding.isLoading && binding.movie == null
}