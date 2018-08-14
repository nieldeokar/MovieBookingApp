package com.tentwenty.movieticket.feature.repository

import android.arch.persistence.room.Room
import android.content.Context
import com.tentwenty.movieticket.feature.shared.model.Movie
import com.tentwenty.movieticket.feature.shared.model.MovieResponse
import com.tentwenty.movieticket.network.ApiService
import com.tentwenty.movieticket.room.AppDatabase
import com.tentwenty.movieticket.utils.constants.ApiConstants
import com.tentwenty.movieticket.utils.constants.DBConstants
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
                val appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DBConstants.DB_NAME).build()
                val moviesDao = appDatabase.moviesDao()
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
                val appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DBConstants.DB_NAME).build()
                val movieDao = appDatabase.moviesDao()
                //insert user to Room
                try {
                    movieDao.insert(dataList)
                    e.onSuccess("Successful")
                } catch (exception: IllegalStateException) {
                    e.onError(exception)
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