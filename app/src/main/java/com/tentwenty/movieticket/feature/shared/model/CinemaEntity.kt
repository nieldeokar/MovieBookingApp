package com.tentwenty.movieticket.feature.shared.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.tentwenty.movieticket.feature.shared.converter.DataConverter
import com.tentwenty.movieticket.room.TheaterLayoutWrapper
import com.tentwenty.movieticket.utils.constants.DBConstants
import java.io.IOException
import java.nio.charset.Charset

@TypeConverters(DataConverter::class)
@Entity(tableName = DBConstants.CINEMA_TABLE_NAME)
class CinemaEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "c_id")
        var id: Int = 0,

        @ColumnInfo(name = "cinema_name")
        val name: String = "",

        @ColumnInfo(name = "cinema_location")
        val cinema_location: String = "",

        @ColumnInfo(name = "theaterLayout")
        val theaterLayout: TheaterLayoutWrapper
) {

    companion object {
        fun populateData(context: Context): Array<CinemaEntity> {
            return arrayOf<CinemaEntity>(
                    CinemaEntity(1, "PVR Cinemas", "Bhandup", getJsonFromAssets(context, DBConstants.SITTING_ARNGMNT_BHANDUP)),
                    CinemaEntity(2, "Nirmal Lifestyle", "Nerul", getJsonFromAssets(context, DBConstants.SITTING_ARNGMNT_NERUL)),
                    CinemaEntity(3, "RCity", "Ghatkopar", getJsonFromAssets(context, DBConstants.SITTING_ARNGMNT_GHATKOPAR)),
                    CinemaEntity(4, "R Odean", "VidyVihar", getJsonFromAssets(context, DBConstants.SITTING_ARNGMNT_VIDYAVIHAR)))
        }

        @Throws(IOException::class)
        fun getJsonFromAssets(context: Context, path: String): TheaterLayoutWrapper {
            (context.assets).open(path).let {
                val buffer = ByteArray(it.available())
                it.read(buffer)
                it.close()
                val strJson = String(buffer, Charset.forName("UTF-8"))
                val gson = GsonBuilder().create()
                val type = object : TypeToken<Array<TheaterLayout>>() {}.type
                val list = gson.fromJson<Array<TheaterLayout>>(strJson, type)

                return TheaterLayoutWrapper(list)

            }

        }
    }


}