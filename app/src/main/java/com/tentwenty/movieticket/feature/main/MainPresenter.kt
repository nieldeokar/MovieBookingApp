package com.tentwenty.movieticket.feature.main

import android.util.Log
import com.tentwenty.movieticket.feature.base.BasePresenter
import javax.inject.Inject


class MainPresenter @Inject constructor(private val mainInteractor: MainInteractor) : BasePresenter<MainView>() {
    fun getData() {
        ifViewAttached { view ->
            view.showLoading()
            mainInteractor.getMovieApiRepository().subscribe({ data ->
                view.populateMovies(data)
                view.hideLoading()
            }, { error ->
                Log.d("TTApp", error.localizedMessage)
                view.hideLoading()
            })
        }
    }
}