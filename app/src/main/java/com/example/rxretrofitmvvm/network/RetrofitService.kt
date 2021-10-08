package com.example.rxretrofitmvvm.network

import com.example.rxretrofitmvvm.model.MovieSearch
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/movie")
    fun getMovieSearch(@Query("api_key") apiKey: String,
    @Query("query") text: String): Observable<MovieSearch>
}