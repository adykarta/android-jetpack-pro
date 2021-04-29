import com.dicoding.film.data.source.remote.response.FilmDetailResponse
import com.dicoding.film.data.source.remote.response.FilmListResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("movie/popular?api_key=f936a5466a7c2551520a62d141eabc77&language=en-US")
    fun getFilmList(): Call<FilmListResponse>

    @GET("movie/{id}?api_key=f936a5466a7c2551520a62d141eabc77&language=en-US")
    fun getFilm( @Path("id") id: Int): Call<FilmDetailResponse>

    @GET("tv/popular?api_key=f936a5466a7c2551520a62d141eabc77&language=en-US")
    fun getTvShowList(): Call<FilmListResponse>

    @GET("tv/{id}?api_key=f936a5466a7c2551520a62d141eabc77&language=en-US")
    fun getTvShow( @Path("id") id: Int): Call<FilmDetailResponse>

}