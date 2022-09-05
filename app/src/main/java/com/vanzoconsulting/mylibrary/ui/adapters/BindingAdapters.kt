package com.vanzoconsulting.mylibrary.ui.adapters

import android.text.format.DateFormat.getDateFormat
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.vanzoconsulting.mylibrary.R
import java.util.*

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        GONE
    } else {
        VISIBLE
    }
}

@BindingAdapter("remoteSrc")
fun bindRemoteSrc(view: ImageView, remoteSrc: String?) {
    remoteSrc?.let {
        view.load(it) {
            crossfade(true)
            placeholder(R.drawable.ic_movie)
        }
    }
}

@BindingAdapter("formattedDate")
fun bindReleaseDate(view: TextView, date: Date?) {
    date?.let {
        with(getDateFormat(view.context)) {
            view.text = format(it)
        }
    }
}