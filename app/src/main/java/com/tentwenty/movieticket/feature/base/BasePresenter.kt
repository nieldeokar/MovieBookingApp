package com.tentwenty.movieticket.feature.base

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

abstract class BasePresenter<V : MvpView> : MvpBasePresenter<V>() {
}