package com.tentwenty.movieticket.room

import android.arch.persistence.room.*
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity
import com.tentwenty.movieticket.feature.showtimes.MovieShowTimes
import com.tentwenty.movieticket.utils.constants.DBConstants
import io.reactivex.Single

@Dao
interface ShowTimeDao {

    @Query("SELECT COUNT(*) FROM " + DBConstants.SHOW_TIME_TABLE_NAME)
    fun getCount(): Single<Int>

    @Query("SELECT * FROM " + DBConstants.SHOW_TIME_TABLE_NAME + " WHERE st_id == :id")
    fun getShowTimeById(id: Int): Single<ShowTimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(showtime: ShowTimeEntity)

    @Update()
    fun update(showtime: ShowTimeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(showtimes: List<ShowTimeEntity>)

    @Query("SELECT * FROM show_times INNER JOIN movies ON show_times.movie_id = movies.m_id " +
            " INNER JOIN cinema ON show_times.cinema_id = cinema.c_id ")
    fun getAllShowTimes() : Single<List<MovieShowTimes>>

    @Query("SELECT * FROM show_times INNER JOIN movies ON show_times.movie_id = movies.m_id " +
            " INNER JOIN cinema ON show_times.cinema_id = cinema.c_id WHERE movies.m_id=:movieId")
    fun getAllShowTimesForMovie(movieId : Int) : Single<List<MovieShowTimes>>



}