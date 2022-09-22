package com.osovan.micartelera.ui.list

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.osovan.micartelera.data.models.Movie
import com.osovan.micartelera.databinding.ActivityMainBinding
import com.osovan.micartelera.ui.detail.DetailActivity
import com.osovan.micartelera.util.Constants.Companion.DEFAULT_REGION
import com.osovan.micartelera.util.Constants.Companion.MOVIE_EXTRA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

     private val moviesViewModel: MoviesViewModel by viewModels()
     private lateinit var binding: ActivityMainBinding
     private lateinit var moviesAdapter: MoviesAdapter
     private lateinit var fusedLocationClient: FusedLocationProviderClient

     private val requestPermissionLauncher =
          registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
               requestMovies(isGranted)
          }

     @SuppressLint("MissingPermission")
     private fun requestMovies(isLocationGranted: Boolean) {
          if (isLocationGranted){
               fusedLocationClient.lastLocation.addOnSuccessListener {
                    Log.d("Oscar", "location: $it")
                    if (it!=null) {
                         getMovies(getLastLocation(it))
                    }else{
                         getMovies(DEFAULT_REGION)
                    }

               }
          }else{
               getMovies(DEFAULT_REGION)
          }

     }



     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(binding.root)

          fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

          moviesAdapter = MoviesAdapter()

          requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)

          initRecyclerView()
          observeMovies()
          setOnMovieClick()
          disableViews()
     }


     private fun getLastLocation(location: Location): String {
          if (location == null) {
               return DEFAULT_REGION
          }

          val geocoder = Geocoder(this)
          val result = geocoder.getFromLocation(
               location.latitude,
               location.longitude,
               1
          )
          Log.d("Oscar", "getLastLocation: ${result.firstOrNull()?.countryCode?: DEFAULT_REGION}")
          return result.firstOrNull()?.countryCode ?: DEFAULT_REGION
     }


     private fun initRecyclerView() {
          binding.rvMovieList.apply {
               adapter = moviesAdapter
          }
     }

     private fun getMovies(region: String) {
          moviesViewModel.getMovies(region)
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
          })
     }
}