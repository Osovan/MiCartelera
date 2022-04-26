package com.osovan.micartelera.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.osovan.micartelera.R
import com.osovan.micartelera.databinding.ActivityDetailBinding
import com.osovan.micartelera.model.Movie

class DetailActivity : AppCompatActivity() {

     companion object {
          const val EXTRA_MOVIE = "DetailActivity:movie"
     }

     private lateinit var binding: ActivityDetailBinding

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding = ActivityDetailBinding.inflate(layoutInflater)
          setContentView(binding.root)

          val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

          if (movie != null) {
               title = movie.title
               Glide
                    .with(binding.root.context)
                    .load("https://image.tmdb.org/t/p/w780/${movie.backdrop_path}")
                    .into(binding.ivDetailBackdrop)
               binding.tvDetailOverview.text = movie.overview
               binding.tvDetailReleaseDate.text = movie.release_date
          }
     }
}