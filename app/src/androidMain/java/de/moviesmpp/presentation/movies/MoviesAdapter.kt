package de.moviesmpp.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.R
import de.moviesmpp.domain.model.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var movies: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        with(holder) {
            Glide.with(posterView).load(movie.completePosterPath).into(posterView)
            titleView.text = movie.title
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val posterView: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        val titleView: TextView = itemView.findViewById(R.id.tv_movie_title)
    }
}