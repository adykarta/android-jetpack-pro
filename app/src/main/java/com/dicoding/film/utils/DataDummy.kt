package com.dicoding.film.utils
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.data.source.remote.response.FilmDetailResponse
import com.dicoding.film.data.source.remote.response.GenreResponse
import com.dicoding.film.data.source.remote.response.TvDetailResponse

object DataDummy {
    fun generateDummyFilm(): List<FilmEntity> {

        val film = ArrayList<FilmEntity>()
        val arrGenre = ArrayList<GenreResponse>()
        val genre = GenreResponse(1,"romance")
        arrGenre.add(genre)
        film.add(
            FilmEntity(
                1,
                "A Star Is Born",
                arrGenre,
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                7.5,
                "2018",
                136,
                "https://dummyimage.com/600x400/000/fff",
                false,
                "film"
            )
        )


        return film
    }
    fun generateDummyTvShows(): List<FilmEntity> {

        val tvShows = ArrayList<FilmEntity>()
        val arrGenre = ArrayList<GenreResponse>()
        val genre = GenreResponse(1,"romance")
        arrGenre.add(genre)

        tvShows.add(
            FilmEntity(
                1,
                "Arrow",
                arrGenre,
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                6.6,
                "2021",
                42,
                "https://dummyimage.com/600x400/000/fff",
                false,
                "tvshow"
            )
        )


        return tvShows
    }
    fun generateDummyRemoteTvShows(): ArrayList<TvDetailResponse> {

        val tvShows = ArrayList<TvDetailResponse>()
        val arrGenre = ArrayList<GenreResponse>()
        val genre = GenreResponse(1,"romance")
        arrGenre.add(genre)

        tvShows.add(
            TvDetailResponse(1,
            "Arrow",
            arrGenre,
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            6.6,
            "2021",
            42,
            "https://dummyimage.com/600x400/000/fff")
        )

        return tvShows
    }
    fun generateDummyRemoteFilm(): ArrayList<FilmDetailResponse> {

        val film = ArrayList<FilmDetailResponse>()
        val arrGenre = ArrayList<GenreResponse>()
        val genre = GenreResponse(1,"romance")
        arrGenre.add(genre)
        film.add(
            FilmDetailResponse(1,
            "A Star Is Born",
            arrGenre,
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            7.5,
            "2018",
            136,
            "https://dummyimage.com/600x400/000/fff")
        )


        return film
    }


}