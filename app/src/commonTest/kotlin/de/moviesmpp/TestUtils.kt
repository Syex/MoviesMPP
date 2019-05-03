package de.moviesmpp

import com.soywiz.klock.DateTime
import de.moviesmpp.domain.model.Movie
import de.moviesmpp.domain.model.PopularMovies

object TestUtils {

    val movie = Movie(
        popularity = 1.0,
        id = 1,
        video = true,
        voteCount = 1,
        voteAverage = 1.0,
        title = "title",
        originalLanguage = "English",
        releaseDate = DateTime.now(),
        originalTitle = "originalTitle",
        genreIds = emptyList(),
        backdropPath = "",
        adult = false,
        overview = "overview",
        posterPath = ""
    )

    val popularMovies = PopularMovies(1, 1, 1, listOf(movie))
}