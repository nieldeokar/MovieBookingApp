package com.tentwenty.movieticket.feature.main

import com.tentwenty.movieticket.feature.repository.MovieApiRepository
import javax.inject.Inject

class MainInteractor @Inject constructor(private val movieApiRepository: MovieApiRepository) {
    fun getMovieApiRepository() = movieApiRepository.getData()
}