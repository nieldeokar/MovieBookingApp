package com.tentwenty.movieticket.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.tentwenty.movieticket.feature.shared.model.CinemaEntity
import com.tentwenty.movieticket.utils.constants.DBConstants
import io.reactivex.Single


@Dao
interface CinemaDao {

    @Query("SELECT * FROM " + DBConstants.CINEMA_TABLE_NAME)
    fun getAllCinemas() : Single<List<CinemaEntity>>

    @Query("SELECT * FROM " + DBConstants.CINEMA_TABLE_NAME + " WHERE id == :id")
    fun getCinemaById(id: Int): Single<CinemaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cinema: CinemaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cinema: Array<CinemaEntity>)

    @Query("DELETE FROM " + DBConstants.CINEMA_TABLE_NAME)
    fun deleteAll()

}