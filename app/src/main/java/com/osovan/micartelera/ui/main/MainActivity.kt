package com.osovan.micartelera.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.osovan.micartelera.databinding.ActivityMainBinding
import com.osovan.micartelera.model.Movie

class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(binding.root)

          binding.rvMovies.adapter = MoviesAdapter(
               listOf(
                    Movie("pelicula 1", "https://loremflickr.com/320/240?lock=1"),
                    Movie("pelicula 2", "https://loremflickr.com/320/240?lock=2"),
                    Movie("pelicula 3", "https://loremflickr.com/320/240?lock=3"),
                    Movie("pelicula 4", "https://loremflickr.com/320/240?lock=4"),
                    Movie("pelicula 5", "https://loremflickr.com/320/240?lock=5"),
                    Movie("pelicula 6", "https://loremflickr.com/320/240?lock=6"),
                    Movie("pelicula 7", "https://loremflickr.com/320/240?lock=7"),
                    Movie("pelicula 8", "https://loremflickr.com/320/240?lock=8"),
                    Movie("pelicula 9", "https://loremflickr.com/320/240?lock=9"),
                    Movie("pelicula 10", "https://loremflickr.com/320/240?lock=10")
               ),
               { movie ->
                    Toast.makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT).show()

               }

          )
     }

}