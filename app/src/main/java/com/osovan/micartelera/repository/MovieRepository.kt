package com.osovan.micartelera.repository

import com.osovan.micartelera.data.remote.MoviedbApiClient
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDbApi: MoviedbApiClient) {

     suspend fun getMovies() = movieDbApi.getMovies()
}