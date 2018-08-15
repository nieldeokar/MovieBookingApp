package com.tentwenty.movieticket.feature.payment

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.tentwenty.movieticket.R
import com.tentwenty.movieticket.TenTwentyApp
import com.tentwenty.movieticket.feature.base.BaseActivity
import com.tentwenty.movieticket.feature.repository.PaymentPresenter
import com.tentwenty.movieticket.feature.shared.model.CardInfo
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class PaymentActivity : BaseActivity<PaymentView, PaymentPresenter>(), PaymentView {

    companion object {
        const val BUNDLE_EXTRA_SHOW_TIME_ID = "show_time_id"
        const val BUNDLE_EXTRA_SEAT_NO = "seat_no"
    }

    @Inject lateinit var paymentPresenter: PaymentPresenter

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

            if(seatNumber.isNotBlank())
                presenter.processBooking(cardObj,seatNumber)
            else
                showToast(getString(R.string.invalid_seat))
        }else{
            showToast(getString(R.string.error_validate_input_payment))
        }

    }

    override fun redirectToBookingConfirmation(orderId: Long) {
        showToast("OID $orderId")
    }

    override fun createPresenter() = paymentPresenter

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
