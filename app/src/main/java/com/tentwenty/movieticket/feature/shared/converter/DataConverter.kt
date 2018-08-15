package com.tentwenty.movieticket.feature.shared.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tentwenty.movieticket.feature.shared.model.TheaterLayout
import com.tentwenty.movieticket.room.TheaterLayoutWrapper

class DataConverter {

    @TypeConverter
    fun fromTheaterLayout(theaterLayouts: TheaterLayoutWrapper?): String? {
        if (theaterLayouts == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Array<TheaterLayout>>() {}.type
        return gson.toJson(theaterLayouts.getTheaterLayouts(), type)
    }

    @TypeConverter
    fun toTheaterLayout(theaterLayoutsString: String?): TheaterLayoutWrapper? {
        if (theaterLayoutsString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Array<TheaterLayout>>() {}.type
        val list = gson.fromJson<Array<TheaterLayout>>(theaterLayoutsString, type)

        return TheaterLayoutWrapper(list)
    }
}