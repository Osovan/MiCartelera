package com.osovan.micartelera.data.remote

import com.osovan.micartelera.data.models.MovieDbResult
import com.osovan.micartelera.util.Constants.Companion.POPULAR
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviedbApiClient {

     @GET(POPULAR)
     suspend fun getMovies(
          @Query("api_key") apiKey: String
//          @Query("region") region: String
     ): Response<MovieDbResult>
}