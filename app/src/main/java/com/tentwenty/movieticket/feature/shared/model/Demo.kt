package com.tentwenty.movieticket.feature.shared.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey



data class Demo(val data: List<Data>)

@Entity()
data class Data(
        @PrimaryKey
        val id: Int,
        val name: String,
        val imageUrl: String
)