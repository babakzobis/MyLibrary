package com.vanzoconsulting.mylibrary.ui.movie

import android.os.Parcelable

sealed class UiState<T>: Parcelable {
    abstract val isLoading: Boolean
    abstract val userMessage: String?
    abstract val value: T?

    abstract fun eraseUserMessage(): UiState<T>
}
