package de

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import de.moviesmpp.ServiceLocator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val api by lazy { ServiceLocator.moviesApi }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            try {
                val popularMovies = api.getPopularMovies()
                Log.i("TEST", popularMovies)
            } catch (e: Exception) {
                Log.e("TEST", e.message)
            }
        }
    }
}