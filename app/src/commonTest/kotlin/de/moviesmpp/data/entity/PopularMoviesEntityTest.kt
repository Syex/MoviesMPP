package de.moviesmpp.data.entity

import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

@UnstableDefault
class PopularMoviesEntityTest {

    private val json = "{\n" +
            "    \"page\": 1,\n" +
            "    \"total_results\": 412221,\n" +
            "    \"total_pages\": 20612,\n" +
            "    \"results\": [\n" +
            "    ]\n" +
            "}"

    @Test
    fun `parses json to PopularMoviesEntity`() {
        val movies = Json.parse(PopularMoviesEntity.serializer(), json)

        assertEquals(1, movies.page)
        assertEquals(412221, movies.totalResults)
        assertEquals(20612, movies.totalPages)
    }
}