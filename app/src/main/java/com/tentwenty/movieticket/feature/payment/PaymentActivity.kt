package com.tentwenty.movieticket.feature.payment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.tentwenty.movieticket.R
import com.tentwenty.movieticket.TenTwentyApp
import com.tentwenty.movieticket.feature.base.BaseActivity
import com.tentwenty.movieticket.feature.main.MainActivity
import com.tentwenty.movieticket.feature.repository.PaymentPresenter
import com.tentwenty.movieticket.feature.shared.model.CardInfo
import com.tentwenty.movieticket.feature.shared.model.OrdersEntity
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class PaymentActivity : BaseActivity<PaymentView, PaymentPresenter>(), PaymentView {
    companion object {

        const val BUNDLE_EXTRA_SHOW_TIME_ID = "show_time_id"
        const val BUNDLE_EXTRA_SEAT_NO = "seat_no"
    }
    @Inject lateinit var paymentPresenter: PaymentPresenter

    var bookingSuccess = false

    var seatNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TenTwentyApp).getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        setSupportActionBar(toolbar)

        val mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar.setDisplayUseLogoEnabled(true)
            mActionBar.setDisplayHomeAsUpEnabled(true)
            mActionBar.setHomeButtonEnabled(true)
        }

        if(intent.hasExtra(BUNDLE_EXTRA_SEAT_NO) && intent.hasExtra(BUNDLE_EXTRA_SHOW_TIME_ID)){

            btnSubmit.isEnabled = true

            seatNumber = intent.getStringExtra(BUNDLE_EXTRA_SEAT_NO)
            val showTimesId = intent.getIntExtra(BUNDLE_EXTRA_SHOW_TIME_ID,0)
            Log.d("Xais","seatNumberIs $seatNumber showId $showTimesId")

            presenter.getShowTimeData(showTimesId)

        } else{
            showToast(getString(R.string.unknown_source))
        }

    }


    fun submitClicked(view: View){
        val cardObj = CardInfo(etCardName.text.toString(),etCardName.text.toString(),
                etCardValidFrom.text.toString(),etCardValidTill.text.toString(),etCvv.text.toString())

        if(presenter.isCardValid(cardObj)){

            if(seatNumber.isNotBlank()) {
                presenter.processBooking(cardObj, seatNumber)
                btnSubmit.isClickable = false
            }else
                showToast(getString(R.string.invalid_seat))
        }else{
            showToast(getString(R.string.error_validate_input_payment))
        }

    }

    override fun redirectToBookingConfirmation(orderId: Long) {
        hideKeyBoard()

        if(orderId != 0L ) {
            bookingSuccess = true
            showToast(getString(R.string.booking_success))
            // This call should be inside BookingConfirmation Activity
            presenter.getOrderData(orderId)
        }

    }

    override fun showOrderDetails(ordersEntity: OrdersEntity) {

        tvEncryptedDetails.text = ordersEntity.toString()

        ordersEntity.cardDetails = presenter.getDecryptedCardDetails(ordersEntity)

        tvDecryptedDetails.text = ordersEntity.toString()
    }

    override fun createPresenter() = paymentPresenter

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(bookingSuccess){
            redirectToMainActivity()
        }else{
            super.onBackPressed()
        }
    }

    private fun redirectToMainActivity() {
        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
