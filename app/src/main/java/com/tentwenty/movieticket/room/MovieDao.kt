package com.tentwenty.movieticket.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.tentwenty.movieticket.feature.shared.model.Movie
import com.tentwenty.movieticket.utils.constants.DBConstants
import io.reactivex.Single


@Dao
interface MovieDao {
    @Query("SELECT * FROM " + DBConstants.MOVIES_TABLE_NAME)
    fun getAllMovies() : Single<List<Movie>>

    @Query("SELECT * FROM " + DBConstants.MOVIES_TABLE_NAME + " WHERE id == :id")
    fun getMovieById(id: Int): Single<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>)

    @Query("DELETE FROM " + DBConstants.MOVIES_TABLE_NAME)
    fun deleteAll()
}
