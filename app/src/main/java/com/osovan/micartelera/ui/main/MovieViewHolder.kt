package com.osovan.micartelera.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osovan.micartelera.databinding.MovieItemBinding
import com.osovan.micartelera.model.Movie

class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

     fun render(movie: Movie) {
          binding.tvMovieTitle.text = movie.title
          binding.rbMovieRating.numStars = 3
          Glide
               .with(binding.root.context)
               .load(movie.cover)
               .into(binding.ivMoviePoster)
     }

}
