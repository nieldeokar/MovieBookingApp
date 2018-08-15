package com.tentwenty.movieticket.feature.repository

import android.content.Context
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity
import com.tentwenty.movieticket.room.AppDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SeatSelectionApiRepository @Inject constructor(){

    @Inject
    lateinit var context: Context

    fun getData(showId : Int): Single<ShowTimeEntity>  = getDataFromDb(showId)

    private fun getDataFromDb(showId : Int): Single<ShowTimeEntity> =
            Single.create { e ->
                val showTimesDao = AppDatabase.getInstance(context).showTimesDao()

                showTimesDao.getShowTimeById(showId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ dataEntity ->
                            e.onSuccess(dataEntity)
                        }, {
                            e.onError(it)
                        })
            }

}
