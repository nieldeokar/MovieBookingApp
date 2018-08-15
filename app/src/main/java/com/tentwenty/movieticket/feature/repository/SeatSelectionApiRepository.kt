package com.tentwenty.movieticket.feature.repository

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.tentwenty.movieticket.feature.shared.model.CinemaEntity
import com.tentwenty.movieticket.feature.shared.model.TheaterLayout
import com.tentwenty.movieticket.room.AppDatabase
import com.tentwenty.movieticket.utils.constants.DBConstants
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.nio.charset.Charset
import java.util.*
import javax.inject.Inject

class SeatSelectionApiRepository @Inject constructor(){

    @Inject
    lateinit var context: Context

    fun getData(): Single<CinemaEntity>  = getDataFromDb()

    private fun getTheaterLayout(): Array<TheaterLayout> {
        val jsonStr = getJsonFromAssets()
        Log.d("A",jsonStr)
        val gson = GsonBuilder().create()


        return  gson.fromJson(jsonStr, Array<TheaterLayout>::class.java)


    }

    private fun getDataFromDb(): Single<CinemaEntity> =
            Single.create { e ->
                val cinemaDao = AppDatabase.getInstance(context).cinemasDao()

                cinemaDao.getCinemaById((0 until 5).random())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ dataEntity ->
                            e.onSuccess(dataEntity)
                        }, {
                            e.onError(it)
                        })
            }

     @Throws(IOException::class)
     fun getJsonFromAssets(): String {
         (context.assets).open(DBConstants.SITTING_ARNGMNT_BHANDUP).let {
             val buffer = ByteArray(it.available())
             it.read(buffer)
             it.close()
             return String(buffer, Charset.forName("UTF-8"))
         }

     }
}

fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive + 1) - start) +  start