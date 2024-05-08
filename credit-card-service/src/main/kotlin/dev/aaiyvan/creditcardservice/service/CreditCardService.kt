package dev.aaiyvan.creditcardservice.service

import dev.aaiyvan.creditcardservice.model.dto.CreditCardRequest
import dev.aaiyvan.creditcardservice.model.dto.CreditCardResponse
import dev.aaiyvan.creditcardservice.model.entity.CreditCard

interface CreditCardService {

    fun createCreditCard(creditCardRequest: CreditCardRequest): CreditCardResponse

    fun deleteCreditCard(creditCardId: String)

    fun getAllCreditCards(): List<CreditCardResponse>

    fun getInfoCreditCard(creditCardId: String): CreditCardResponse

    fun getCreditCard(creditCardId: String): CreditCard

}