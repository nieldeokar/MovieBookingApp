package com.tentwenty.movieticket.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.tentwenty.movieticket.feature.shared.model.Data
import com.tentwenty.movieticket.feature.shared.model.Movie


@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}