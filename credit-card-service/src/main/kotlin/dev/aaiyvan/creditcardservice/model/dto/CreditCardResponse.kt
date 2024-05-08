package dev.aaiyvan.creditcardservice.model.dto

data class CreditCardResponse(
    var number: String? = null,
    var expirationDate: String? = null,
    var cvv: String? = null,
    var cardHolderName: String? = null
)