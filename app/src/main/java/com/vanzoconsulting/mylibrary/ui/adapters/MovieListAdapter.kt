package com.vanzoconsulting.mylibrary.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vanzoconsulting.mylibrary.databinding.ListItemMovieBinding
import com.vanzoconsulting.mylibrary.domain.entity.Movie
import com.vanzoconsulting.mylibrary.ui.movie.MovieListFragmentDirections


class MovieListAdapter : ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffCallback()) {

    class ViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            binding.apply {
                movie = item
                setClickListener { v ->
                    navigateToDetails(v, item)
                }
                executePendingBindings()
            }
        }

        private fun navigateToDetails(view: View, movie: Movie) {
            val direction =
                MovieListFragmentDirections.actionFragmentMovieListToFragmentMovieDetails(movie)
            view.findNavController().navigate(direction)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.posterUrl == newItem.posterUrl

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.posterUrl == newItem.posterUrl
    }
}