package de.moviesmpp.domain.model

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.parse
import de.moviesmpp.data.entity.MovieEntity

private const val MOVIE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/original"

private val releaseDateFormat = DateFormat("yyyy-MM-dd")

data class Movie(
    val popularity: Double,
    val id: Int,
    val video: Boolean,
    val voteCount: Int,
    val voteAverage: Double,
    val title: String,
    val releaseDate: DateTime,
    val originalLanguage: String,
    val originalTitle: String,
    val genreIds: List<Int>,
    val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    val posterPath: String
) {

    val completePosterPath = MOVIE_POSTER_BASE_URL + posterPath
}

fun MovieEntity.toModel() = Movie(
    popularity = popularity,
    id = id,
    video = video,
    voteCount = voteCount,
    voteAverage = voteAverage,
    title = title,
    releaseDate = releaseDateFormat.parse(releaseDate).local,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    genreIds = genreIds,
    backdropPath = backdropPath,
    adult = adult,
    overview = overview,
    posterPath = posterPath
)