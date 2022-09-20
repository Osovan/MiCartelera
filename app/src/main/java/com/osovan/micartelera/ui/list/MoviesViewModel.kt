package com.osovan.micartelera.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osovan.micartelera.R
import com.osovan.micartelera.data.models.MovieDbResult
import com.osovan.micartelera.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
     ViewModel() {

     private val movies = MutableLiveData<MovieDbResult>()

     fun getMovies(region: String) = viewModelScope.launch {
          movieRepository.getMovies(region).let {
               if (it.isSuccessful) {
                    movies.value = it.body()
               } else {
                    Log.e("Error: ", it.code().toString())
               }
          }
     }

     fun observeMovies(): LiveData<MovieDbResult>{
          return movies
     }

}