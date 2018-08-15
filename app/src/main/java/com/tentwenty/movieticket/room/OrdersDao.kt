package com.tentwenty.movieticket.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.tentwenty.movieticket.feature.shared.model.OrdersEntity
import com.tentwenty.movieticket.utils.constants.DBConstants
import io.reactivex.Single


@Dao
interface OrdersDao {
    @Query("SELECT * FROM " + DBConstants.ORDERS_TABLE_NAME)
    fun getAllOrders() : Single<List<OrdersEntity>>

    @Query("SELECT * FROM " + DBConstants.ORDERS_TABLE_NAME + " WHERE o_id == :id")
    fun getOrderById(id: Long): Single<OrdersEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(order: OrdersEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orders: List<OrdersEntity>)

    @Query("DELETE FROM " + DBConstants.ORDERS_TABLE_NAME)
    fun deleteAll()
}
