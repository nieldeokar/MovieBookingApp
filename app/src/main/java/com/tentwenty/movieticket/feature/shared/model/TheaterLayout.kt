package com.tentwenty.movieticket.feature.shared.model

import com.google.gson.annotations.SerializedName

data class TheaterLayout(
        @SerializedName("row") val rowName: String = "",
        @SerializedName("values") val values: List<Int>
)