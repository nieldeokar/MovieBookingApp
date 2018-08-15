package com.tentwenty.movieticket.feature.showtimes

import android.arch.persistence.room.Embedded
import com.tentwenty.movieticket.feature.shared.model.CinemaEntity
import com.tentwenty.movieticket.feature.shared.model.Movie
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity

data class MovieShowTimes(
        @Embedded val showTime: ShowTimeEntity,
        @Embedded val movie: Movie,
        @Embedded val cinema: CinemaEntity
)