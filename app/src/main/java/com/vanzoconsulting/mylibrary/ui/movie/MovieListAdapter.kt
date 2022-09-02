package com.vanzoconsulting.mylibrary.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vanzoconsulting.mylibrary.R
import com.vanzoconsulting.mylibrary.databinding.ListItemMovieBinding
import com.vanzoconsulting.mylibrary.domain.entity.Movie


class MovieListAdapter : ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffCallback()) {

    class ViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            binding.listItemMovieTitle.text = item.title
            binding.listItemMovieYear.text = item.year.toString()
            binding.listItemMovieDirector.text = item.director
            binding.listItemMovieImage.load(item.posterUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.posterUrl == newItem.posterUrl

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.posterUrl == newItem.posterUrl
    }
}