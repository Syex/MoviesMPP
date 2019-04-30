package de.moviesmpp.presentation.popularmovies

import de.moviesmpp.domain.model.Movie
import de.moviesmpp.domain.usecase.GetPopularMovies
import de.moviesmpp.domain.usecase.UseCase
import de.moviesmpp.presentation.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PopularMoviesPresenter(
    private val getPopularMovies: GetPopularMovies,
    coroutineContext: CoroutineContext = Dispatchers.Default
) : BasePresenter<PopularMoviesView>(coroutineContext) {

    override fun onViewAttached(view: PopularMoviesView) {
        view.setLoadingVisible(true)
        getPopularMovies()
    }

    private fun getPopularMovies() {
        scope.launch {
            getPopularMovies(
                UseCase.None,
                onSuccess = { view?.setPopularMovies(it.results) },
                onFailure = { view?.showMoviesFailedToLoad() }
            )
            view?.setLoadingVisible(false)
        }
    }
}

interface PopularMoviesView {

    fun setPopularMovies(movies: List<Movie>)

    fun showMoviesFailedToLoad()

    fun setLoadingVisible(visible: Boolean)
}