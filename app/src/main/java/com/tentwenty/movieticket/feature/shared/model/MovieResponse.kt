package com.tentwenty.movieticket.feature.shared.model

import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("results")
    val movies: List<Movie> = ArrayList()
}
