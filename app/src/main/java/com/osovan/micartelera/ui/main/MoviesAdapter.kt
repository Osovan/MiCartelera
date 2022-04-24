package com.osovan.micartelera.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osovan.micartelera.databinding.MovieItemBinding
import com.osovan.micartelera.model.Movie


class MoviesAdapter(
     private val moviesList: List<Movie>,
     private val onMovieClickListener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
          val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          return MovieViewHolder(binding)
     }

     override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
          val movie = moviesList[position]
          holder.render(movie)
          holder.itemView.setOnClickListener {
               onMovieClickListener(movie)
          }
     }

     override fun getItemCount() = moviesList.size

}
