package tokopedia.app.data.repository.moviedetail

import retrofit2.Response
import tokopedia.app.data.entity.Movie
import tokopedia.app.data.repository.movie.MovieDetailRepository
import tokopedia.app.data.routes.NetworkServices

class MovieDetailRepositoryImpl constructor(
    private val service: NetworkServices
): MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: String): Response<Movie> {
       return service.getMovieDetail(movieId)
    }


}