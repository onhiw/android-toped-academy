package com.example.moviedetail.ui

import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviedetail.R
import com.example.moviedetail.domain.MovieDetailUseCase
import com.example.moviedetail.factory.DetailMovieFactory
import kotlinx.android.synthetic.main.detail_movie.*
import tokopedia.app.abstraction.base.BaseActivity
import tokopedia.app.abstraction.util.ext.load
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.repository.movie.MovieDetailRepository
import tokopedia.app.data.repository.moviedetail.MovieDetailRepositoryImpl
import tokopedia.app.data.routes.NetworkServices
import tokopedia.app.network.Network
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailActivity: BaseActivity() {
    override fun contentView(): Int = R.layout.detail_movie

    private lateinit var repository: MovieDetailRepository
    private lateinit var useCase: MovieDetailUseCase
    private lateinit var viewModel: DetailMovieViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        intent?.data?.lastPathSegment.let {

            it?.let { it1 -> viewModel.setMovieId(it1) }
        }

        viewModel.error.observe(this, onShowError())

        viewModel.movie.observe(this, Observer {
            progressBar.visibility = View.VISIBLE
            if (it != null){
                progressBar.visibility = View.INVISIBLE
               // showToast(it.title)
                showMovieDetail(it)
            }

        })
    }

    override fun initObservable() {

        val networkBuilder = Network.builder().create(NetworkServices::class.java)

        repository = MovieDetailRepositoryImpl(networkBuilder)

        useCase = MovieDetailUseCase(repository)

        viewModel = ViewModelProviders.of(this, DetailMovieFactory(useCase)).get(DetailMovieViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showMovieDetail(movie: Movie){
        var date = LocalDate.parse(movie.releaseDate)
        var formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        var formatterDate = date.format(formatter)
        imgBanner.load(movie.bannerUrl())
        imgPoster.load(movie.posterUrl())
        txtMovieName.text = movie.title
        txtYear.text = formatterDate
        txtContent.text = movie.overview
        txtRating.text = movie.voteAverage.toString()
        txtVote.text = movie.voteCount.toString()

    }

    private fun onShowError(): Observer<String>{
        return Observer {
            showToast(it)
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}