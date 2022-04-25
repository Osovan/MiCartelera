package com.osovan.micartelera.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.osovan.micartelera.R
import com.osovan.micartelera.databinding.ActivityMainBinding
import com.osovan.micartelera.model.Movie
import com.osovan.micartelera.model.MovieDbClient
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(binding.root)

          val moviesAdapter = MoviesAdapter(emptyList()) { movie ->
               Toast.makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT).show()
          }

          binding.rvMovies.adapter = moviesAdapter

          thread {
               val apiKey = getString(R.string.api_key)
               val popularMovies = MovieDbClient.service.getPopularMovies(apiKey)
               val body = popularMovies.execute().body()

               runOnUiThread {
                    if (body != null) {
                         moviesAdapter.moviesList = body.results
                         moviesAdapter.notifyDataSetChanged()
                    }
               }

          }
     }

}