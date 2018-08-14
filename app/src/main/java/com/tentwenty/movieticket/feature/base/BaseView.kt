package com.tentwenty.movieticket.feature.base

import com.hannesdorfmann.mosby3.mvp.MvpView


interface BaseView: MvpView {
    fun showLoading()

    fun hideLoading()
}