package com.vanzoconsulting.mylibrary.data

sealed class DataResource<out T> {

    data class Success<out T>(val value: T) : DataResource<T>()
    data class Failure(val message: String? = null) : DataResource<Nothing>()
}
