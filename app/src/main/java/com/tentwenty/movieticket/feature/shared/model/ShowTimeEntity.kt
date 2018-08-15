package com.tentwenty.movieticket.feature.shared.model

import android.arch.persistence.room.*
import com.tentwenty.movieticket.feature.shared.converter.DataConverter
import com.tentwenty.movieticket.room.TheaterLayoutWrapper
import com.tentwenty.movieticket.utils.constants.DBConstants

@Entity(
        tableName = DBConstants.SHOW_TIME_TABLE_NAME,
        foreignKeys = arrayOf(ForeignKey(entity = CinemaEntity::class,
                parentColumns = arrayOf("c_id"),
                childColumns = arrayOf("cinema_id"),
                onDelete = ForeignKey.CASCADE),

                ForeignKey(entity = Movie::class,
                        parentColumns = arrayOf("m_id"),
                        childColumns = arrayOf("movie_id"),
                        onDelete = ForeignKey.CASCADE)
        )
)
@TypeConverters(DataConverter::class)
class ShowTimeEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "st_id")
        var id: Int = 0,

        @ColumnInfo(name = "location")
        var location: String = "",

        @ColumnInfo(name = "movie_id")
        var movId: Int = 0,

        @ColumnInfo(name = "cinema_id")
        var cinemaId: Int = 0,

        @ColumnInfo(name = "time")
        var timings: String = "",

        @ColumnInfo(name = "seat_map")
        val theaterLayout: TheaterLayoutWrapper


)




