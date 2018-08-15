package com.tentwenty.movieticket.feature.shared.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.tentwenty.movieticket.utils.constants.DBConstants

@Entity(tableName = DBConstants.SHOW_TIME_TABLE_NAME,
        foreignKeys = arrayOf(ForeignKey(entity = CinemaEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("cinema_id"),
        onDelete = ForeignKey.CASCADE),

        ForeignKey(entity = Movie::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("movie_id"),
                onDelete = ForeignKey.CASCADE)
))
class ShowTime {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "location")
    var location: String = ""

    @ColumnInfo(name = "movie_id")
    var movId : Int = 0

    @ColumnInfo(name = "cinema_id")
    var cinemaId: Int = 0

    @ColumnInfo(name = "time")
    var timings: String = ""

    @ColumnInfo(name = "seat_map")
    var seatMap: String = ""
}




