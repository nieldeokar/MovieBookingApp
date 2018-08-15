package com.tentwenty.movieticket.feature.payment

import com.tentwenty.movieticket.feature.base.BaseView
import com.tentwenty.movieticket.feature.shared.model.OrdersEntity


interface PaymentView: BaseView {
    fun redirectToBookingConfirmation(orderId : Long)

    fun showOrderDetails(ordersEntity: OrdersEntity)
}