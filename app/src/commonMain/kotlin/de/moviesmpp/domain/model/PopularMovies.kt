package de.moviesmpp.domain.model

import de.moviesmpp.data.entity.PopularMoviesEntity

data class PopularMovies(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<Movie>
)

fun PopularMoviesEntity.toModel() = PopularMovies(
    page = page,
    totalPages = totalPages,
    totalResults = totalResults,
    results = results.map { it.toModel() }
)
