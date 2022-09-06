package com.vanzoconsulting.mylibrary.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import com.vanzoconsulting.mylibrary.R
import com.vanzoconsulting.mylibrary.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private val viewModel: MovieListViewModel by viewModels()
    private val movieListAdapter = MovieListAdapter()
    private lateinit var _binding: FragmentMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                viewModel.uiState.collect { uiState ->
                    _binding.isLoading = uiState.isLoading
                    _binding.hasMovies = uiState.movies.isNotEmpty()
                    movieListAdapter.submitList(uiState.movies)
                    uiState.userMessage?.let {
                        showUserMessage(it)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        _binding.fragmentMovieListMain.adapter = movieListAdapter

        return _binding.root
    }

    private fun showUserMessage(message: String) {
        val snackbar = Snackbar.make(_binding.root, message, LENGTH_INDEFINITE)
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    viewModel.userMessageShown()
                }
            })

        if (!_binding.isLoading && !_binding.hasMovies) {
            snackbar.setAction(R.string.fragment_movie_list_retry) {
                viewModel.reset()
            }
        }

        snackbar.show()
    }
}