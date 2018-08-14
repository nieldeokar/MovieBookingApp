package com.tentwenty.movieticket.feature.repository

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.tentwenty.movieticket.feature.shared.model.TheaterLayout
import com.tentwenty.movieticket.utils.constants.DBConstants
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

class SeatSelectionApiRepository @Inject constructor(){

    @Inject
    lateinit var context: Context

    fun getData(): List<TheaterLayout> = getTheaterLayout()

    private fun getTheaterLayout(): List<TheaterLayout> {
        val jsonStr = getJsonFromAssets()
        Log.d("A",jsonStr)
        return GsonBuilder().create().fromJson(jsonStr,ArrayList<TheaterLayout>()::class.java)

    }

     @Throws(IOException::class)
     fun getJsonFromAssets(): String {
         (context.assets).open(DBConstants.SITTING_ARRANGEMENT_JSON_PATH).let {
             val buffer = ByteArray(it.available())
             it.read(buffer)
             it.close()
             return String(buffer, Charset.forName("UTF-8"))
         }

     }
}