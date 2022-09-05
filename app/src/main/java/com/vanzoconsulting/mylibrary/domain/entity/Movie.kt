package com.vanzoconsulting.mylibrary.domain.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vanzoconsulting.mylibrary.network.BackDropUrl
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
@JsonClass(generateAdapter = true)
/**
 * todo: local storage via Room and sync it with the API
 */
data class Movie(
    val title: String? = null,
    val year: Int? = null,
    val director: String? = null,
    val posterUrl: String? = null,
    var id: Int? = null,
    val overview: String? = null,
    @field:Json(name =  "release_date")
    val releaseDate: Date? = null,
    @BackDropUrl
    @field:Json(name =  "backdrop_path")
    val backdropUrl: String? = null,
    @field:Json(name = "vote_average")
    val averageVote: Double? = null
) : Parcelable {
    val safeYear: Int?
        get() = year.takeIf { it != null } ?: releaseDate?.let {
            Calendar.getInstance().apply { time = releaseDate }.get(Calendar.YEAR)
        }
}

