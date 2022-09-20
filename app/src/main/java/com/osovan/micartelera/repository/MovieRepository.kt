package com.osovan.micartelera.repository

import com.osovan.micartelera.data.remote.MoviedbApiClient
import javax.inject.Inject
import javax.inject.Named

class MovieRepository @Inject constructor(
     private val movieDbApi: MoviedbApiClient,
     @Named("apiKey") private val apiKey: String
) {

     suspend fun getMovies() = movieDbApi.getMovies(apiKey)
}