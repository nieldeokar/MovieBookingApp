package com.tentwenty.movieticket.feature.order

import android.os.Bundle
import com.tentwenty.movieticket.R
import com.tentwenty.movieticket.TenTwentyApp
import com.tentwenty.movieticket.feature.base.BaseActivity
import com.tentwenty.movieticket.feature.shared.model.OrdersEntity
import kotlinx.android.synthetic.main.activity_order_confirmation.*
import javax.inject.Inject

class OrderConfirmationActivity : BaseActivity<OrderView,OrderPresenter>(), OrderView {

    companion object {
        const val ORDER_ID = "order_id"
    }

    @Inject
    lateinit var orderPresenter : OrderPresenter

    override fun createPresenter() = orderPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TenTwentyApp).getApplicationComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        if(intent.hasExtra(ORDER_ID)){
            presenter.getData(intent.getLongExtra(ORDER_ID,0L))
        }
    }

    override fun showOrderDetails(ordersEntity: OrdersEntity) {
        showToast(ordersEntity.toString())

        event_name_tv.text = ordersEntity.showId.toString()
        seats_no_tv.text = ordersEntity.seatNumber
    }

}
