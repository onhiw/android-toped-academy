package com.example.movie.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.domain.PopularMovieUseCase
import com.example.movie.ui.PopularMovieViewModel

class PopularMovieFactory(private val useCase: PopularMovieUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMovieViewModel(useCase) as T
    }
}