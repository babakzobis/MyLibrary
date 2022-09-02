package com.vanzoconsulting.mylibrary.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.vanzoconsulting.mylibrary.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private val viewModel: MovieListViewModel by viewModels()
    private val movieListAdapter = MovieListAdapter()
    private lateinit var _binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        _binding.fragmentMovieListMain.apply {
            adapter = movieListAdapter
            layoutManager = GridLayoutManager(this@MovieListFragment.context, 2)
        }
        movieListAdapter.registerAdapterDataObserver(
            MovieListAdapterObserver(
                _binding.fragmentMovieListMain,
                _binding.fragmentMovieListEmpty
            )
        )

        return _binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieListAdapter.submitList(movies)
        }
    }
}