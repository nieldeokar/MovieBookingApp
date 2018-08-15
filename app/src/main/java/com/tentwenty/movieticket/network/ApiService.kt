package com.tentwenty.movieticket.network

import com.tentwenty.movieticket.feature.shared.model.MovieResponse
import com.tentwenty.movieticket.utils.constants.ApiConstants
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET(ApiConstants.UPCOMING_MOVIES_PATH)
    fun getUpcomingMovies(@Query("api_key") key : String): Flowable<MovieResponse>
}