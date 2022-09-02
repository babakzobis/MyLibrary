package com.vanzoconsulting.mylibrary.di

import com.vanzoconsulting.mylibrary.data.MovieRepository
import com.vanzoconsulting.mylibrary.data.remote.MovieRepositoryRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun provideMovieRepositoryRemote(repository: MovieRepositoryRemote): MovieRepository
}