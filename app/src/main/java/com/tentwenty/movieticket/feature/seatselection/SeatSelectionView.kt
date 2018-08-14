package com.tentwenty.movieticket.feature.seatselection

import com.tentwenty.movieticket.feature.base.BaseView
import com.tentwenty.movieticket.feature.shared.model.TheaterLayout


interface SeatSelectionView: BaseView {
    fun renderSeats(seatsList: ArrayList<TheaterLayout>)
}