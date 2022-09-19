package com.osovan.micartelera.ui.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.osovan.micartelera.R
import com.osovan.micartelera.data.models.Movie
import com.osovan.micartelera.databinding.ActivityDetailBinding
import com.osovan.micartelera.util.Constants.Companion.MOVIE_EXTRA
import com.osovan.micartelera.util.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

     private val detailViewModel : DetailViewModel by viewModels()
     private lateinit var binding: ActivityDetailBinding
     private lateinit var  movie: Movie


     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding = ActivityDetailBinding.inflate(layoutInflater)
          setContentView(binding.root)

          setSupportActionBar(binding.toolbar)
          supportActionBar?.setDisplayHomeAsUpEnabled(true)

          getMovieFromIntent()

          detailViewModel.getMovie(movie)

          detailViewModel.observeMovie().observe(this) {

               binding.toolbar.title = movie.title
               binding.detailBackdrop.load("https://image.tmdb.org/t/p/w780${movie.backdrop_path}")

               bindingDetailInfo(binding.detailInfo, movie)
               binding.detailSummary.text = movie.overview

          }


     }

     private fun bindingDetailInfo(detailInfo: TextView, movie: Movie) {
          detailInfo.text = buildSpannedString {
               appendInfo(R.string.original_language, movie.original_language)
               appendInfo(R.string.original_title, movie.original_title)
               appendInfo(R.string.release_date, movie.release_date)
               appendInfo(R.string.popularity, movie.popularity.toString())
               appendInfo(R.string.vote_average, movie.vote_average.toString())

          }
     }

     private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String?) {

          bold {
               append(getString(stringRes))
               append(": ")
          }
          appendLine(value)
     }

     private fun getMovieFromIntent() {
           movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA)!!
     }













}
