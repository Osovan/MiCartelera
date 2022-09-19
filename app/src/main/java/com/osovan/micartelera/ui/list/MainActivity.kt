package com.osovan.micartelera.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.osovan.micartelera.data.models.Movie
import com.osovan.micartelera.databinding.ActivityMainBinding
import com.osovan.micartelera.ui.detail.DetailActivity
import com.osovan.micartelera.util.Constants.Companion.MOVIE_EXTRA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

     private val moviesViewModel: MoviesViewModel by viewModels()
     private lateinit var  binding: ActivityMainBinding
     private lateinit var moviesAdapter: MoviesAdapter

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(binding.root)

          moviesAdapter = MoviesAdapter()

          initRecyclerView()
          getMovies()
          observeMovies()
          setOnMovieClick()
          disableViews()
     }


     private fun initRecyclerView() {
          binding.rvMovieList.apply {
               adapter = moviesAdapter
          }
     }

     private fun getMovies() {
          moviesViewModel.getMovies()
     }

     private fun observeMovies() {
          moviesViewModel.observeMovies().observe(this) {
               moviesAdapter.setMovies(it.results)
               enableViews()
          }
     }

     private fun enableViews() {
          binding.rvMovieList.visibility = View.VISIBLE
          binding.pbProgressBar.visibility = View.GONE
     }

     private fun disableViews() {
          binding.rvMovieList.visibility = View.GONE
          binding.pbProgressBar.visibility = View.VISIBLE
     }

     private fun setOnMovieClick() {
          moviesAdapter.setOnMovieClick(object : MoviesAdapter.OnMovieClick {
               override fun onMovieClick(movie: Movie) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra(MOVIE_EXTRA, movie)
                    startActivity(intent)
               }
          } )
     }
}