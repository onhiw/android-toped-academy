package tokopedia.app.data.repository.movie

import retrofit2.Response
import tokopedia.app.data.entity.Movie

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: String): Response<Movie>
}