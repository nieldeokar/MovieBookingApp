package com.tentwenty.movieticket.utils.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun convertDate(inputString: String?): String? {

        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

        try {
            return sdf.format(SimpleDateFormat("yyyy-M-dd", Locale.getDefault()).parse(inputString))
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return inputString
    }
}
