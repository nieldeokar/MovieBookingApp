package com.tentwenty.movieticket.feature.repository

import android.content.Context
import android.util.Log
import com.tentwenty.movieticket.feature.shared.model.Movie
import com.tentwenty.movieticket.network.ApiService
import com.tentwenty.movieticket.room.AppDatabase
import com.tentwenty.movieticket.utils.constants.ApiConstants
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MovieApiRepository @Inject constructor() {
    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var context: Context

    fun getData(): Single<List<Movie>> = getDataFromDb()

    private fun getDataFromDb(): Single<List<Movie>> =
            Single.create { e ->
                val moviesDao = AppDatabase.getInstance(context).moviesDao()
                moviesDao.getAllMovies()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ dataList ->
                            if (dataList.isNotEmpty()) {
                                e.onSuccess(dataList)
                            } else {
                                getDataFromApi(e)
                            }
                        }, {
                            getDataFromApi(e)
                        })
            }

    private fun insertToDatabase(dataList: List<Movie>) =
            Single.create<String> { e ->
                try {
                    val moviesDao = AppDatabase.getInstance(context).moviesDao()
                    moviesDao.insert(dataList)
                    Log.d("MovieApiRepository","SuccessInsert")
                    e.onSuccess("Successful")
                } catch (exception: IllegalStateException) {
                    e.onError(exception)
                    Log.d("MovieApiRepository","Success")
                }
            }

    private fun getDataFromApi(emitter: SingleEmitter<List<Movie>>) =
            apiService.getUpcomingMovies(ApiConstants.API_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        if (response != null) {
                            insertToDatabase(response.movies)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.newThread())
                                    .subscribe({
                                        emitter.onSuccess(response.movies)
                                    }, { error ->
                                        emitter.onError(error)
                                    })
                        } else
                            emitter.onError(Throwable("Error API call"))
                    }, { error ->
                        emitter.onError(error)
                    })
}