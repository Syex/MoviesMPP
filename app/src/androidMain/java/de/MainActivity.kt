package de

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import de.moviesmpp.MoviesAdapter
import de.moviesmpp.ServiceLocator
import de.moviesmpp.domain.model.Movie
import de.moviesmpp.presentation.popularmovies.PopularMoviesView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PopularMoviesView {

    private val logTag = MainActivity::class.java.simpleName

    private val presenter by lazy { ServiceLocator.popularMoviesPresenter }

    private val moviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movies.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = moviesAdapter
        }
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
        Log.i(logTag, "Setting these movies in view $movies")
        moviesAdapter.movies = movies
    }

    override fun showMoviesFailedToLoad() {
        Toast.makeText(this, "Movies failed to load", Toast.LENGTH_LONG).show()
    }

    override fun setLoadingVisible(visible: Boolean) {
        clpb_movies.visibility = if (visible) View.VISIBLE else View.GONE
    }
}