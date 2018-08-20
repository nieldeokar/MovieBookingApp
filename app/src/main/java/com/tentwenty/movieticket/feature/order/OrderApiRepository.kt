package com.tentwenty.movieticket.feature.order

import android.content.Context
import android.util.Log
import com.tentwenty.movieticket.feature.shared.model.CinemaEntity
import com.tentwenty.movieticket.feature.shared.model.Movie
import com.tentwenty.movieticket.feature.shared.model.OrdersEntity
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity
import com.tentwenty.movieticket.network.ApiService
import com.tentwenty.movieticket.room.AppDatabase
import com.tentwenty.movieticket.utils.constants.ApiConstants
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class OrderApiRepository @Inject constructor() {

    @Inject
    lateinit var context: Context

    fun getData(orderId: Long): Single<OrdersEntity> = getDataFromDb(orderId)


    private fun getDataFromDb(orderId: Long): Single<OrdersEntity> =
            Single.create { e ->
                val ordersDao = AppDatabase.getInstance(context).ordersDao()

                ordersDao.getOrderById(orderId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ dataEntity ->
                            e.onSuccess(dataEntity)
                        }, {
                            e.onError(it)
                        })
            }

}

