package com.tentwenty.movieticket.feature.shared.model

data class CardInfo(
        var cardNumber : String = "",
        var cardName : String = "",
        var validFrom : String = "",
        var validTill : String = "",
        var cvvNumber : String = ""
)
