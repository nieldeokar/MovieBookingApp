package com.tentwenty.movieticket.feature.showtimes

import android.util.Log
import com.tentwenty.movieticket.feature.base.BasePresenter
import javax.inject.Inject

class ShowTimesPresenter  @Inject constructor(private val showTimesInteractor: ShowTimesInteractor) : BasePresenter<ShowTimesView>() {
    fun getData() {
        ifViewAttached { view ->
          view.showLoading()

            showTimesInteractor.getShowTimes().subscribe({ data ->
                view.renderShowTimes(data)
                view.hideLoading()
            }, { error ->
                Log.d("Xais", error.localizedMessage)
                view.hideLoading()
            })

        }
    }

    fun getData(movieId : Int) {
        ifViewAttached { view ->
            view.showLoading()

            showTimesInteractor.getShowTimesForMovie(movieId).subscribe({ data ->
                view.renderShowTimes(data)
                view.hideLoading()
            }, { error ->
                Log.d("Xais", error.localizedMessage)
                view.hideLoading()
            })

        }
    }
}