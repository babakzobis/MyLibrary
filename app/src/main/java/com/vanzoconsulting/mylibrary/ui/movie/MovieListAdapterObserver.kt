package com.vanzoconsulting.mylibrary.ui.movie

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView

class MovieListAdapterObserver constructor(private val rv: RecyclerView, private val ev: View) :
    RecyclerView.AdapterDataObserver() {

    init {
        checkIfEmpty()
    }

    override fun onChanged() {
        super.onChanged()

        checkIfEmpty()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        super.onItemRangeChanged(positionStart, itemCount)

        checkIfEmpty()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)

        checkIfEmpty()
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        super.onItemRangeRemoved(positionStart, itemCount)

        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        val emptyViewVisible = rv.adapter?.let { it.itemCount == 0 } ?: true
        ev.visibility = if (emptyViewVisible) VISIBLE else GONE
        rv.visibility = if (emptyViewVisible) GONE else VISIBLE
    }

}