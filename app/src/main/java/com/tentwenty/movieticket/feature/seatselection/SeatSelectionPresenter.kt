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
            val list = seatSelectionInteractor.getSittingArrangement()

            val model1 = TheaterLayout("F", Arrays.asList(1,1,1,1,1,1,1,1))
            val model2 = TheaterLayout("E", Arrays.asList(2,2,1,1,1,1,1,1))
            val model3 = TheaterLayout("", Arrays.asList(0,0,0,0,0,0,0,0))
            val model4 = TheaterLayout("D", Arrays.asList(1,1,1,0,0,1,1,1))
            val model5 = TheaterLayout("C", Arrays.asList(1,1,1,0,0,1,2,1))
            val model6 = TheaterLayout("B", Arrays.asList(1,1,1,0,0,1,1,1))
            val model7 = TheaterLayout("A", Arrays.asList(2,2,1,1,1,1,2,2))

//            val list = ArrayList<TheaterLayout>()

           /* list.add(model1)
            list.add(model2)
            list.add(model3)
            list.add(model4)
            list.add(model5)
            list.add(model6)
            list.add(model7)*/
            view.hideLoading()
            view.renderSeats(list)
        }
    }
}