package com.tentwenty.movieticket.feature.repository

import android.content.Context
import com.tentwenty.movieticket.feature.showtimes.MovieShowTimes
import com.tentwenty.movieticket.room.AppDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ShowTimesApiRepository @Inject constructor() {

    @Inject
    lateinit var context: Context

    fun getData(): Single<List<MovieShowTimes>> = getDataFromDb()

    fun getData(movieId : Int): Single<List<MovieShowTimes>> = getDataFromDb(movieId)

    private fun getDataFromDb(): Single<List<MovieShowTimes>> =
            Single.create { e ->
                val showTimesDao = AppDatabase.getInstance(context).showTimesDao()
                showTimesDao.getAllShowTimes()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ dataList ->
                            e.onSuccess(dataList)
                        }, {
                            e.onError(it)
                        })
            }


    private fun getDataFromDb(movieId: Int): Single<List<MovieShowTimes>> =
            Single.create { e ->
                val showTimesDao = AppDatabase.getInstance(context).showTimesDao()
                showTimesDao.getAllShowTimesForMovie(movieId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ dataList ->
                            e.onSuccess(dataList)
                        }, {
                            e.onError(it)
                        })
            }



}
