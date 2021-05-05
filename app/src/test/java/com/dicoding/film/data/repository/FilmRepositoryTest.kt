import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.film.data.repository.FakeFilmRepository
import com.dicoding.film.data.source.remote.api.ApiCall
import com.dicoding.film.data.source.remote.api.ApiCallback
import com.dicoding.film.data.source.remote.response.FilmDetailResponse
import com.dicoding.film.data.source.remote.response.TvDetailResponse
import com.dicoding.film.utils.DataDummy
import com.dicoding.film.utils.LiveDataTestUtil
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(ApiCall::class.java)
    private val filmRepository = FakeFilmRepository(remote)
    private val filmResponses = DataDummy.generateDummyRemoteFilm()

    private val filmId = filmResponses[0].id

    private val detailFilm = filmResponses[0]
    private val tvResponses = DataDummy.generateDummyRemoteTvShows()
    private val tvId = tvResponses[0].id

    private val detailTv= tvResponses[0]

    @Test
    fun getAllFilm() {
        doAnswer { invocation ->
            (invocation.arguments[0] as ApiCallback<ArrayList<FilmDetailResponse>>).onCallSuccess(filmResponses)
            null
        }.`when`(remote).loadFilmList(any())
        val filmEntities = LiveDataTestUtil.getValue(filmRepository.getAllFilm())
        verify(remote).loadFilmList(any())
        assertNotNull(filmEntities)
        assertEquals(filmResponses.size.toLong(), filmEntities.size.toLong())
    }
    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as ApiCallback<ArrayList<TvDetailResponse>>).onCallSuccess(tvResponses)

            null
        }.`when`(remote).loadTvShowList(any())
        val filmEntities = LiveDataTestUtil.getValue(filmRepository.getAllTvShow())
        verify(remote).loadTvShowList(any())
        assertNotNull(filmEntities)
        assertEquals(tvResponses.size.toLong(), filmEntities.size.toLong())
    }


    @Test
    fun getDetailFilm() {
        doAnswer { invocation ->
            (invocation.arguments[1] as ApiCallback<FilmDetailResponse>).onCallSuccess(detailFilm)
            null
        }.`when`(remote).loadDetailFilm(eq(filmId), any())

        val filmEntitiesContent = LiveDataTestUtil.getValue(filmRepository.getDetailFilm(filmId))
        val mockDetailFilmEntity = FilmDetailResponse(
            filmEntitiesContent.id,
            filmEntitiesContent.title,
            filmEntitiesContent.genre,
            filmEntitiesContent.overview,
            filmEntitiesContent.userScore,
            filmEntitiesContent.releaseYear,
            filmEntitiesContent.duration,
            filmEntitiesContent.photo
        )
        verify(remote)
            .loadDetailFilm(eq(filmId), any())


        assertNotNull(mockDetailFilmEntity)
        assertEquals(detailFilm, mockDetailFilmEntity)
    }

    @Test
    fun getDetailTv() {
        doAnswer { invocation ->
            (invocation.arguments[1] as ApiCallback<TvDetailResponse>).onCallSuccess(detailTv)
            null
        }.`when`(remote).loadDetailTvShow(eq(tvId), any())

        val filmEntitiesContent = LiveDataTestUtil.getValue(filmRepository.getDetailTv(tvId))
        val mockDetailFilmEntity = TvDetailResponse(
            filmEntitiesContent.id,
            filmEntitiesContent.title,
            filmEntitiesContent.genre,
            filmEntitiesContent.overview,
            filmEntitiesContent.userScore,
            filmEntitiesContent.releaseYear,
            filmEntitiesContent.duration,
            filmEntitiesContent.photo
        )
        verify(remote)
            .loadDetailTvShow(eq(tvId), any())


        assertNotNull(mockDetailFilmEntity)
        assertEquals(detailTv, mockDetailFilmEntity)
    }
}


