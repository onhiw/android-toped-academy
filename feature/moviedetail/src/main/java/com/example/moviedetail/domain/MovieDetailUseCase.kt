package com.example.moviedetail.domain

import tokopedia.app.abstraction.util.state.ResultState
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.repository.movie.MovieDetailRepository

class MovieDetailUseCase ( private val repository : MovieDetailRepository){
    suspend fun get(movieId : String) : ResultState<Movie>{
        val detailMovie = repository.getMovieDetail(movieId)
        return if (detailMovie.isSuccessful){
            ResultState.Success(detailMovie.body()!!)
        } else {
            ResultState.Error(MOVIE_ERROR)
        }
    }

    companion object {
        private const val  MOVIE_ERROR =  " yah gagal"
    }
}