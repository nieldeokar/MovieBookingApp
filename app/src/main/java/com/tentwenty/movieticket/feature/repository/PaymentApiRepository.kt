package com.tentwenty.movieticket.feature.repository

import android.content.Context
import android.util.Log
import com.tentwenty.movieticket.feature.shared.model.OrdersEntity
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity
import com.tentwenty.movieticket.room.AppDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PaymentApiRepository @Inject constructor() {

    @Inject
    lateinit var context: Context

    fun getData(showId: Int): Single<ShowTimeEntity> = getDataFromDb(showId)

    fun getOrderDetails(orderId: Long): Single<OrdersEntity> = getOrderDataFromDb(orderId)

    fun saveData(showTimes: ShowTimeEntity, ordersEntity: OrdersEntity): Single<Long> = saveDataIntoDb(showTimes, ordersEntity)

    private fun saveDataIntoDb(showTimes: ShowTimeEntity, ordersEntity: OrdersEntity): Single<Long> =

        Single.create<Long> { e ->
            try {
                val showTimesDao = AppDatabase.getInstance(context).showTimesDao()
                showTimesDao.update(showTimes)

                for (layout in showTimes.theaterLayout.getTheaterLayouts()!!){

                    Log.d("TTApp",layout.rowName)
                    Log.d("TTApp",layout.values.toString())
                }

                val ordersDaoObj = AppDatabase.getInstance(context).ordersDao()

                val orderid = ordersDaoObj.insert(ordersEntity)

                e.onSuccess(orderid)

            } catch (ex: Exception) {
                e.onError(ex)
            }

        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())




    private fun getDataFromDb(showId: Int): Single<ShowTimeEntity> =
            Single.create { e ->
                val showTimesDao = AppDatabase.getInstance(context).showTimesDao()

                showTimesDao.getShowTimeById(showId)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ dataEntity ->
                            e.onSuccess(dataEntity)
                        }, {
                            e.onError(it)
                        })
            }


    private fun getOrderDataFromDb(orderId: Long): Single<OrdersEntity> =
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
