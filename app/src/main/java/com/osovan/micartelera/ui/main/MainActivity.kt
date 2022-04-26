package com.osovan.micartelera.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.osovan.micartelera.R
import com.osovan.micartelera.databinding.ActivityMainBinding
import com.osovan.micartelera.model.MovieDbClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding
     private val moviesAdapter = MoviesAdapter(emptyList()) {}

     private lateinit var fusedLocationClient: FusedLocationProviderClient
     private val requestPermissionLauncher =
          registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
               requestPopularMovies(isGranted)
          }


     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          binding = ActivityMainBinding.inflate(layoutInflater)
          setContentView(binding.root)

          fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

          binding.rvMovies.adapter = moviesAdapter

          requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)


     }

     @SuppressLint("MissingPermission")
     private fun requestPopularMovies(isLocationGranted: Boolean) {
          if (isLocationGranted) {
               fusedLocationClient.lastLocation.addOnCompleteListener {
                    doRequestPopularMovies(getRegionFromLocation(it.result))
               }
          } else {
               doRequestPopularMovies("US")
          }


     }

     private fun doRequestPopularMovies(region: String) {
          lifecycleScope.launch {
               val apiKey = getString(R.string.api_key)
               val popularMovies = MovieDbClient.service.getPopularMovies(apiKey, region)
               moviesAdapter.moviesList = popularMovies.results
               moviesAdapter.notifyDataSetChanged()
          }

     }


     private fun getRegionFromLocation(location: Location?): String {

          if (location == null) {
               return "US"
          }

          val geocoder = Geocoder(this)
          val result = geocoder.getFromLocation(
               location.latitude,
               location.longitude,
               1
          )
          return result.firstOrNull()?.countryCode ?: "US"
     }


}