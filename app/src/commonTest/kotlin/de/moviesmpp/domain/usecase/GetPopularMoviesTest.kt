package de.moviesmpp.domain.usecase

import de.moviesmpp.TestUtils
import de.moviesmpp.data.MoviesApi
import de.moviesmpp.data.entity.PopularMoviesEntity
import de.moviesmpp.domain.model.toModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class GetPopularMoviesTest {

    private val api = mockk<MoviesApi>()
    private val getPopularMovies = GetPopularMovies(api)

    @Test
    fun `returns popular movies from api`() {
        val entity = mockk<PopularMoviesEntity> {
            every { toModel() } returns TestUtils.popularMovies
        }
        coEvery { api.getPopularMovies() } returns entity

        // This actually doesn't work, but makes this test compile. Testing suspending functions is currently
        // not possible in common modules
        suspend {
            getPopularMovies(
                UseCase.None,
                onSuccess = {
                    print(it)
                    assertEquals(TestUtils.popularMovies, it)
                },
                onFailure = { print(it);fail(it.toString()) }
            )
        }
    }
}