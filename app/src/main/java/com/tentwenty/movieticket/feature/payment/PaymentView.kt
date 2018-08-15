package com.tentwenty.movieticket.feature.payment

import com.tentwenty.movieticket.feature.base.BaseView


interface PaymentView: BaseView {
    fun redirectToBookingConfirmation(orderId : Long)
}