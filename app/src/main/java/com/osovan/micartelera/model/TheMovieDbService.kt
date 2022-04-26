package com.osovan.micartelera.model

import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

     @GET("movie/popular?language=es")
     suspend fun getPopularMovies(
          @Query("api_key") apikey: String,
          @Query("region") region: String
     ): MovieResult
}