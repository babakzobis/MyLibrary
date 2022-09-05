package com.vanzoconsulting.mylibrary.domain

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import com.vanzoconsulting.mylibrary.domain.entity.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MovieSearchResult(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int
) : Parcelable
