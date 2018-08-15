package com.tentwenty.movieticket.di.component

import com.tentwenty.movieticket.di.module.ApplicationModule
import com.tentwenty.movieticket.feature.main.MainActivity
import com.tentwenty.movieticket.feature.payment.PaymentActivity
import com.tentwenty.movieticket.feature.seatselection.SeatSelectionActivity
import com.tentwenty.movieticket.feature.showtimes.ShowTimesActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(baseActivity: MainActivity)

    fun inject(seatSelectionActivity: SeatSelectionActivity)

    fun inject(showTimesActivity: ShowTimesActivity)

    fun inject(paymentActivity: PaymentActivity)
}