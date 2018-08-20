package com.tentwenty.movieticket.feature.order

import android.util.Log
import com.tentwenty.movieticket.feature.base.BasePresenter
import javax.inject.Inject


class OrderPresenter @Inject constructor(private val orderInteractor: OrderInteractor) : BasePresenter<OrderView>() {
    fun getData(orderId : Long) {
        ifViewAttached { view ->
            view.showLoading()
            orderInteractor.getOrderApiRepository(orderId).subscribe({ data ->
                view.showOrderDetails(data)
                view.hideLoading()
            }, { error ->
                Log.d("TTApp", error.localizedMessage)
                view.hideLoading()
            })
        }
    }
}