package com.tentwenty.movieticket.feature.shared.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey



data class Timings(val data: List<Time>)

@Entity()
data class Time(
        @PrimaryKey
        val id: Int,
        val time: String
)