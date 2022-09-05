package com.vanzoconsulting.mylibrary.network

import com.squareup.moshi.*

class BackdropAdapter: JsonAdapter<String>() {

    @FromJson
    @BackDropUrl
    override fun fromJson(reader: JsonReader): String? {
        if (reader.peek() != JsonReader.Token.NULL) {
            val string = reader.nextString()

            return BASE_BACKDROP_PATH + string
        }

        return reader.nextNull<String>()
    }

    @ToJson
    override fun toJson(writer: JsonWriter, @BackDropUrl value: String?) {
        value?.let { writer.value(value) } ?: writer.nullValue()
    }

    companion object {
        const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"
    }
}