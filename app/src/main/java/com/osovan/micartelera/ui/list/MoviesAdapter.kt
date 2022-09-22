package com.osovan.micartelera.ui.list

import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osovan.micartelera.data.models.Movie
import com.osovan.micartelera.databinding.ViewMovieItemBinding
import com.osovan.micartelera.util.Constants.Companion.INIT_POSTER_PATH
import com.osovan.micartelera.util.load

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

     private var movies: List<Movie> = ArrayList()
     private lateinit var onMovieClick: OnMovieClick

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
          val view =
               ViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return ViewHolder(view)
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          val movie = movies[position]

          holder.binding.apply {
               ivCover.load(INIT_POSTER_PATH + movie.poster_path)
               tvMovieTitle.text = movie.title
               val averageInt =(10*(movie.vote_average!!)).toInt()
               progressBar.progress = averageInt
               tvProgress.text = "$averageInt%"

          }

          holder.binding.mCardView.setOnClickListener {
               onMovieClick.onMovieClick(movie)
          }
     }

     override fun getItemCount(): Int {
          return movies.size
     }

     class ViewHolder(val binding: ViewMovieItemBinding) : RecyclerView.ViewHolder(binding.root)


     fun setMovies(movies: List<Movie>) {
          this.movies = movies
     }

     interface OnMovieClick {
          fun onMovieClick(movie: Movie)
     }

     fun setOnMovieClick(onMovieClick: OnMovieClick) {
          this.onMovieClick = onMovieClick
     }

}