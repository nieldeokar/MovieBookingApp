package com.tentwenty.movieticket.feature.seatselection

import android.util.Log
import com.tentwenty.movieticket.feature.base.BasePresenter
import com.tentwenty.movieticket.feature.main.MainInteractor
import com.tentwenty.movieticket.feature.main.MainView
import com.tentwenty.movieticket.feature.shared.model.TheaterLayout
import java.util.*
import javax.inject.Inject

class SeatSelectionPresenter @Inject constructor(private val seatSelectionInteractor: SeatSelectionInteractor) : BasePresenter<SeatSelectionView>() {
    fun getData() {
        ifViewAttached { view ->
            view.showLoading()
//            val list = seatSelectionInteractor.getSittingArrangement()

            val model1 = TheaterLayout("A", Arrays.asList(1,1,1,0,0,1,1,1))
            val model2 = TheaterLayout("B", Arrays.asList(1,1,1,0,0,1,2,1))
            val model3 = TheaterLayout("C", Arrays.asList(1,1,1,0,0,1,1,1))
            val model4 = TheaterLayout("D", Arrays.asList(1,2,1,1,1,1,1,1))

            val list = ArrayList<TheaterLayout>()

            list.add(model1)
            list.add(model2)
            list.add(model3)
            list.add(model4)
            view.hideLoading()
            view.renderSeats(list)
        }
    }
}