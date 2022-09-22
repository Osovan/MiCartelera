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

     private val detailViewModel: DetailViewModel by viewModels()
     private lateinit var binding: ActivityDetailBinding
     private lateinit var movie: Movie


     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding = ActivityDetailBinding.inflate(layoutInflater)
          setContentView(binding.root)

          getMovieFromIntent()
          supportActionBar?.setDisplayHomeAsUpEnabled(true)
          supportActionBar?.title = movie.title


          detailViewModel.getMovie(movie)

          detailViewModel.observeMovie().observe(this) {
               binding.apply {
                    ivDetailBackdrop.load("https://image.tmdb.org/t/p/w780${movie.backdrop_path}")
                    tvOverview.text = movie.overview
                    bindingDetailInfo(tvMoreInfo, movie)
                    rbRatting.rating = ((movie.vote_average?.times(5))?.div(10))?.toFloat() ?: 0f
                    tvVotes.text = "(${movie.vote_count} votos)"
                    tvDate.text = movie.release_date
               }
          }
     }

     private fun bindingDetailInfo(detailInfo: TextView, movie: Movie) {
          detailInfo.text = buildSpannedString {
               appendInfo(R.string.original_language, movie.original_language)
               appendInfo(R.string.original_title, movie.original_title)
               appendInfo(R.string.popularity, movie.popularity.toString())
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
          movie = intent.getParcelableExtra(MOVIE_EXTRA)!!
     }


}
