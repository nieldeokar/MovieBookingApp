package com.tentwenty.movieticket.feature.seatselection

import com.tentwenty.movieticket.feature.repository.SeatSelectionApiRepository
import javax.inject.Inject

class SeatSelectionInteractor @Inject constructor(private val selectionApiRepository: SeatSelectionApiRepository) {
    fun getSittingArrangement() = selectionApiRepository.getData()
}