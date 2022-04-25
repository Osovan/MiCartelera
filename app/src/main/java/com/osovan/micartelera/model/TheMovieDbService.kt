package com.osovan.micartelera.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

     @GET("movie/popular")
     fun getPopularMovies(@Query("api_key") apikey: String): Call<MovieResult>
}