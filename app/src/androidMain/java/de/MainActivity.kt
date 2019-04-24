package de

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import de.moviesmpp.ServiceLocator
import de.moviesmpp.domain.model.Movie
import de.moviesmpp.presentation.popularmovies.PopularMoviesView

class MainActivity : AppCompatActivity(), PopularMoviesView {

    private val presenter by lazy { ServiceLocator.popularMoviesPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun setPopularMovies(movies: List<Movie>) {
        Log.i("TEST", movies.toString())
    }

    override fun showMoviesFailedToLoad() {
        Log.e("TEST", "Movies failed to load")
    }
}