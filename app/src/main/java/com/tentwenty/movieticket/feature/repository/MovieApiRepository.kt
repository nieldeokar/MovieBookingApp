package com.tentwenty.movieticket.feature.repository

import android.content.Context
import android.util.Log
import com.tentwenty.movieticket.feature.shared.model.CinemaEntity
import com.tentwenty.movieticket.feature.shared.model.Movie
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity
import com.tentwenty.movieticket.network.ApiService
import com.tentwenty.movieticket.room.AppDatabase
import com.tentwenty.movieticket.utils.constants.ApiConstants
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


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
                    insertShowTimesData(dataList)

                    Log.d("MovieApiRepository", "SuccessInsert")
                    e.onSuccess("Successful")
                } catch (exception: IllegalStateException) {
                    e.onError(exception)
                    Log.d("MovieApiRepository", "Success")
                }
            }

    private fun insertShowTimesData(moviesList: List<Movie>) {

        getCinemaData().subscribe({ data ->

            if(data.isNotEmpty() && moviesList.isNotEmpty()) {
                val showTimeList = ArrayList<ShowTimeEntity>()

                for (cinemaEntity in data) {

                    for (i in 1..5) {

                        val randomMovieIndex = (1 until moviesList.size).random()
                        val movie = moviesList[randomMovieIndex]

                        val showTimeEntity = ShowTimeEntity(0, cinemaEntity.cinema_location, movie.id,
                                cinemaEntity.id, "2:25 PM", cinemaEntity.theaterLayout)

                        showTimeList.add(showTimeEntity)
                    }

                }
                val showTimeDao = AppDatabase.getInstance(context).showTimesDao()

                Log.d("Xais", "inserted ${showTimeList.size} items")
                showTimeDao.insert(showTimeList)
            }

        }, { error ->
            Log.d("Xais", error.localizedMessage)

        })

        checkShowTimeData()

    }

    private fun getCinemaData(): Single<List<CinemaEntity>> =
            Single.create { e ->

                val cinemaDao = AppDatabase.getInstance(context).cinemasDao()
                cinemaDao.getAllCinemas()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.newThread())
                        .subscribe({ dataList ->
                           e.onSuccess(dataList)
                        }, {
                            e.onError(it)
                        })
            }


    private fun checkShowTimeData() {
        Single.create<Int> { e ->
            val showTimeDao = AppDatabase.getInstance(context).showTimesDao()
            showTimeDao.getCount()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ count ->
                        if (count <= 0) {
                            Log.d("AC", "count of Show times $count")

                        } else {
                            e.onSuccess(count)
                        }
                    }, {
                        e.onError(it)
                    })

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


    fun ClosedRange<Int>.random() =
            Random().nextInt((endInclusive + 1) - start) + start
}

