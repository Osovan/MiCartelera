package com.osovan.micartelera.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.osovan.micartelera.R
import com.osovan.micartelera.data.models.Movie
import com.osovan.micartelera.databinding.ActivityMainBinding
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

          Log.d("Oscar", "init moviesAdapter")
          moviesAdapter = MoviesAdapter()

          initRecyclerView()
          getMovies()
          observeMovies()
          setOnMovieClick()
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

     private fun setOnMovieClick() {
          moviesAdapter.setOnMovieClick(object : MoviesAdapter.OnMovieClick {
               override fun onMovieClick(movie: Movie) {

               }
          } )
     }
}