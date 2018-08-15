package com.tentwenty.movieticket.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity
import com.tentwenty.movieticket.utils.constants.DBConstants
import io.reactivex.Single

@Dao
interface ShowTimeDao {

    @Query("SELECT COUNT(*) FROM " + DBConstants.SHOW_TIME_TABLE_NAME)
    fun getCount() : Single<Int>

    @Query("SELECT * FROM " + DBConstants.SHOW_TIME_TABLE_NAME)
    fun getAllShowTimes() : Single<List<ShowTimeEntity>>

    @Query("SELECT * FROM " + DBConstants.SHOW_TIME_TABLE_NAME + " WHERE id == :id")
    fun getShowTimeById(id: Int): Single<ShowTimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(showtime: ShowTimeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(showtimes: List<ShowTimeEntity>)

}