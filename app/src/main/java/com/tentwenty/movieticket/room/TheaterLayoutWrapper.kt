package com.tentwenty.movieticket.room

import com.tentwenty.movieticket.feature.shared.model.TheaterLayout

class TheaterLayoutWrapper(private val theaterLayouts: Array<TheaterLayout>) {

    fun getTheaterLayouts(): Array<TheaterLayout>? {
        return theaterLayouts
    }

}