package com.tentwenty.movieticket.feature.main

import com.tentwenty.movieticket.feature.base.BaseView
import com.tentwenty.movieticket.feature.shared.model.Movie


interface MainView: BaseView {
    fun populateMovies(myList: List<Movie>)
}