package com.osovan.micartelera.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osovan.micartelera.data.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

     private val movie = MutableLiveData<Movie>()

     fun getMovie(_movie: Movie) = viewModelScope.launch {
          movie.postValue(_movie)
     }

     fun observeMovie(): LiveData<Movie> {
          return movie
     }
}