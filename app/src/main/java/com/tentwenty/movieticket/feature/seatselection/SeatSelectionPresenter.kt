package com.tentwenty.movieticket.feature.seatselection

import android.util.Log
import com.tentwenty.movieticket.feature.base.BasePresenter
import javax.inject.Inject

class SeatSelectionPresenter @Inject constructor(private val seatSelectionInteractor: SeatSelectionInteractor) : BasePresenter<SeatSelectionView>() {
    fun getData() {
        ifViewAttached { view ->
            view.showLoading()

            seatSelectionInteractor.getSittingArrangement().subscribe({ data ->
                view.renderSeats(data)
                view.hideLoading()
            }, { error ->
                Log.d("Xais", error.localizedMessage)
                view.hideLoading()
            })

        }
    }
}