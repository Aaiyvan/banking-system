package dev.aaiyvan.creditcardservice.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreditCardRequest(

    @NotNull @NotBlank
    var number: String,

    @NotNull @NotBlank
    var expirationDate: String,

    @NotNull @NotBlank
    var cvv: String,

    @NotNull @NotBlank
    var cardHolderName: String

)