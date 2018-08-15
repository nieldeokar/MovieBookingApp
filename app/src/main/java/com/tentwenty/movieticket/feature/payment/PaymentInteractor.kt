package com.tentwenty.movieticket.feature.payment

import com.tentwenty.movieticket.feature.repository.PaymentApiRepository
import com.tentwenty.movieticket.feature.shared.model.OrdersEntity
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity
import javax.inject.Inject

class PaymentInteractor @Inject constructor(private val paymentApiRepository: PaymentApiRepository) {

    fun getShowTime(showTimeId : Int) = paymentApiRepository.getData(showTimeId)

    fun confirmBooking(showTimeEntity: ShowTimeEntity,ordersEntity: OrdersEntity) = paymentApiRepository.saveData(showTimeEntity,ordersEntity)
}