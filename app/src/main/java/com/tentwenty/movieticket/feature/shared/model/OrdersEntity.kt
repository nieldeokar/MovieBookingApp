package com.tentwenty.movieticket.feature.shared.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.tentwenty.movieticket.utils.constants.DBConstants

@Entity(tableName = DBConstants.ORDERS_TABLE_NAME)
class OrdersEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "o_id")
        var id: Int = 0,

        @ColumnInfo(name = "seat_no")
        var seatNumber: String = "",

        @ColumnInfo(name = "card_details")
        var cardDetails: String = "",

        @ColumnInfo(name = "show_id")
        var showId: Int = 0
) {
    override fun toString(): String {
        return "order_id = $id \nseat_no = $seatNumber \ncard_details = $cardDetails"
    }
}