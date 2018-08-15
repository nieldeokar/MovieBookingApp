package com.tentwenty.movieticket.feature.showtimes

import com.tentwenty.movieticket.feature.base.BaseView

interface ShowTimesView: BaseView {
    fun renderShowTimes(movieShowTimes: List<MovieShowTimes>)
}