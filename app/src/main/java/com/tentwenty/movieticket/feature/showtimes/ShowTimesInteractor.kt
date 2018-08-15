package com.tentwenty.movieticket.feature.showtimes

import com.tentwenty.movieticket.feature.repository.ShowTimesApiRepository
import javax.inject.Inject


class ShowTimesInteractor @Inject constructor(private val showTimesApiRepository: ShowTimesApiRepository) {
    fun getShowTimes() = showTimesApiRepository.getData()

    fun getShowTimesForMovie(movieId : Int) = showTimesApiRepository.getData(movieId)
}