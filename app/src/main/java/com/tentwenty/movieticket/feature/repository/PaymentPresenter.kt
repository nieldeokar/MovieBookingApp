package com.tentwenty.movieticket.feature.repository

import android.util.Log
import com.tentwenty.movieticket.R
import com.tentwenty.movieticket.feature.base.BasePresenter
import com.tentwenty.movieticket.feature.payment.PaymentInteractor
import com.tentwenty.movieticket.feature.payment.PaymentView
import com.tentwenty.movieticket.feature.seatselection.SeatSelectionActivity
import com.tentwenty.movieticket.feature.shared.model.CardInfo
import com.tentwenty.movieticket.feature.shared.model.OrdersEntity
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity
import com.tentwenty.movieticket.utils.util.AESEncyption
import javax.inject.Inject

class PaymentPresenter @Inject constructor(private val paymentInteractor: PaymentInteractor) : BasePresenter<PaymentView>() {

    private lateinit var showTimeEntity: ShowTimeEntity

    fun getShowTimeData(showId: Int) {
        ifViewAttached { view ->
            view.showLoading()

            paymentInteractor.getShowTime(showId).subscribe({ data ->
                showTimeEntity = data
            }, { error ->
                Log.d("TTApp", error.localizedMessage)
                view.showToast(error.localizedMessage)
            })

            view.hideLoading()
        }
    }

    fun processBooking(cardInfo: CardInfo, seatNumber: String) {
        ifViewAttached { view ->
            view.showLoading()

            if (::showTimeEntity.isInitialized) {

                val readableSeatNo = updateSeatStatusToBooked(seatNumber)

                saveBookingIntoDatabase(view, readableSeatNo, cardInfo)

            } else {
                Log.d("TTApp", "ShowTimeEntity not initialized")
                view.hideLoading()
                view.showToast(R.string.invalid_showtime)
            }

        }
    }

    fun getOrderData(orderId: Long) {
        ifViewAttached { view ->
            view.showLoading()

            paymentInteractor.getOrderDetails(orderId).subscribe({ data ->

                view.showOrderDetails(data)
            }, { error ->
                Log.d("TTApp", error.localizedMessage)
                view.showToast(error.localizedMessage)
            })

            view.hideLoading()
        }
    }

    fun getDecryptedCardDetails(ordersEntity: OrdersEntity) : String{
        return AESEncyption.decrypt(ordersEntity.cardDetails,ordersEntity.seatNumber)
    }

    private fun saveBookingIntoDatabase(view: PaymentView, seatNumber: String, cardInfo: CardInfo) {

        val encryptedString = AESEncyption.encrypt(cardInfo.toString(), seatNumber)
        val ordersEntity = OrdersEntity(0, seatNumber, encryptedString)

        paymentInteractor.confirmBooking(showTimeEntity, ordersEntity).subscribe({ data ->
            view.redirectToBookingConfirmation(data)
            view.hideLoading()
        }, { error ->
            Log.d("TTApp", error.localizedMessage)
            view.hideLoading()
        })
    }


    private fun updateSeatStatusToBooked(seatNo: String) : String {
        val list = showTimeEntity.theaterLayout.getTheaterLayouts()!!

        val positionArray = seatNo.split(",")

        val rowPosition = positionArray[0].toInt()
        val columnPosition = positionArray[1].toInt()

        val positionValues = list[rowPosition].values.toMutableList()
        positionValues[columnPosition] = SeatSelectionActivity.SEAT_BOOKED

        list[rowPosition].values = positionValues

        Log.d("TTApp", "postitionValue $positionValues ")

        return list[rowPosition].rowName + (columnPosition + 1)


    }

    fun isCardValid(cardInfo: CardInfo): Boolean {
        return cardInfo.cardName.isNotEmpty() && cardInfo.cardNumber.isNotEmpty() && cardInfo.validFrom.isNotEmpty()
                && cardInfo.validTill.isNotEmpty() && cardInfo.cvvNumber.isNotEmpty()
    }
}