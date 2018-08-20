package com.tentwenty.movieticket.feature.order

import com.tentwenty.movieticket.feature.base.BaseView
import com.tentwenty.movieticket.feature.shared.model.OrdersEntity

interface OrderView : BaseView{

    fun showOrderDetails(ordersEntity: OrdersEntity)
}