package com.vanzoconsulting.mylibrary.domain.entity

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
/**
 * todo: local storage via Room and sync it with the API
 */
data class Movie(
    val title: String? = null,
    val year: Int? = null,
    val director: String? = null,
    val posterUrl: String? = null
) : Parcelable
