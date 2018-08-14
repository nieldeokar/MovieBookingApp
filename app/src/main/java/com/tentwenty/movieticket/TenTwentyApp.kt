package com.tentwenty.movieticket
import android.app.Application
import com.tentwenty.movieticket.di.component.ApplicationComponent
import com.tentwenty.movieticket.di.component.DaggerApplicationComponent
import com.tentwenty.movieticket.di.module.ApplicationModule


class TenTwentyApp : Application() {
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
    }

    private fun initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

    }

    fun getApplicationComponent() = applicationComponent
}