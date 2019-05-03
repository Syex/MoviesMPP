package de.moviesmpp.presentation.popularmovies

import de.moviesmpp.TestUtils
import de.moviesmpp.domain.model.PopularMovies
import de.moviesmpp.domain.usecase.GetPopularMovies
import io.mockk.*
import kotlin.test.Test

class PopularMoviesPresenterTest {

    private val getPopularMovies = mockk<GetPopularMovies>()
    private val presenter = PopularMoviesPresenter(getPopularMovies)
    private val view = mockk<PopularMoviesView>(relaxUnitFun = true)

    @Test
    fun `shows loading when a view attaches`() {
        presenter.attachView(view)

        verify { view.setLoadingVisible(true) }
    }

    @Test
    fun `requests movies and shows them in the view`() {
        val slot = CapturingSlot<(PopularMovies) -> Unit>()
        coEvery { getPopularMovies(any(), capture(slot), any()) } answers { slot.invoke(TestUtils.popularMovies) }

        presenter.attachView(view)
        verify { view.setPopularMovies(listOf(TestUtils.movie)) }
        verify(exactly = 0) { view.showMoviesFailedToLoad() }
        verify { view.setLoadingVisible(false) }
    }

    @Test
    fun `if requesting movies fails, shows an appropriate message`() {
        val slot = CapturingSlot<(Exception) -> Unit>()
        coEvery { getPopularMovies(any(), any(), capture(slot)) } answers { slot.invoke(Exception()) }

        presenter.attachView(view)
        verify(exactly = 0) { view.setPopularMovies(any()) }
        verify { view.showMoviesFailedToLoad() }
        verify { view.setLoadingVisible(false) }
    }
}