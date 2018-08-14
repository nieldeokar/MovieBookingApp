package com.tentwenty.movieticket.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.tentwenty.movieticket.feature.shared.model.Data
import io.reactivex.Single


@Dao
interface DemoDao {
    @Query("SELECT * FROM data")
    fun getAllDemo(): Single<List<Data>>

    @Insert
    fun insetDemo(demoData: List<Data>)
}