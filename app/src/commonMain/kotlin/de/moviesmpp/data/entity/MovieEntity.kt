package de.moviesmpp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieEntity(
    val popularity: Double,
    val id: Int,
    val video: Boolean,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("vote_average") val voteAverage: Double,
    val title: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("genre_ids") val genreIds: List<Int>,
    @SerialName("backdrop_path") val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    @SerialName("poster_path") val posterPath: String
)

/**
"popularity": 437.513,
"id": 299534,
"video": false,
"vote_count": 73,
"vote_average": 7.4,
"title": "Avengers: Endgame",
"release_date": "2019-04-26",
"original_language": "en",
"original_title": "Avengers: Endgame",
"genre_ids": [
12,
878,
28
],
"backdrop_path": "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
"adult": false,
"overview": "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
"poster_path": "/or06FN3Dka5tukK1e9sl16pB3iy.jpg"
 */