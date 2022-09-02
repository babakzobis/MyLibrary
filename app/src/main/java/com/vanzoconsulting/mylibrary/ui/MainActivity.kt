package com.vanzoconsulting.mylibrary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vanzoconsulting.mylibrary.R
import com.vanzoconsulting.mylibrary.ui.movie.MovieListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // todo: replace with Navigation component
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment.newInstance())
                .commitNow()
        }
    }
}