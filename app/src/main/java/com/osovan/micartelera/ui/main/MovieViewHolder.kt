package com.osovan.micartelera.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osovan.micartelera.databinding.MovieItemBinding
import com.osovan.micartelera.model.Movie

class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

     fun render(movie: Movie) {
          Glide
               .with(binding.root.context)
               .load("https://image.tmdb.org/t/p/w300/${movie.poster_path}")
               .into(binding.ivMoviePoster)
          val average = movie.vote_average.toFloat()/2
          binding.rbMovieRating.rating = average
     }

}
