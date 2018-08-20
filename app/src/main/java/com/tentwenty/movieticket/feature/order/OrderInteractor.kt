package com.tentwenty.movieticket.feature.order

import javax.inject.Inject

class OrderInteractor @Inject constructor(private val orderApiRepository: OrderApiRepository) {
    fun getOrderApiRepository(orderId: Long) = orderApiRepository.getData(orderId)
}