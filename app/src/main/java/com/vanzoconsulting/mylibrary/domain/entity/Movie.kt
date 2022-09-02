package com.vanzoconsulting.mylibrary.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
/**
 * todo: local storage via Room
 */
data class Movie(

    @field:Json(name = "title")
    val title: String? = null,

    @field:Json(name = "year")
    val year: Int? = null,

    @field:Json(name = "director")
    val director: String? = null,

    @field:Json(name = "posterUrl")
    val posterUrl: String? = null
)
