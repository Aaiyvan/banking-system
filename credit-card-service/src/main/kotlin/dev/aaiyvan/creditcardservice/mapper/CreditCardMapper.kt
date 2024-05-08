package dev.aaiyvan.creditcardservice.mapper

import dev.aaiyvan.creditcardservice.model.dto.CreditCardRequest
import dev.aaiyvan.creditcardservice.model.dto.CreditCardResponse
import dev.aaiyvan.creditcardservice.model.entity.CreditCard
import org.springframework.stereotype.Component

@Component
class CreditCardMapper {

    fun toCreditCard(creditCardRequest: CreditCardRequest): CreditCard{
        return CreditCard(
            number = creditCardRequest.number,
            expirationDate = creditCardRequest.expirationDate,
            cvv = creditCardRequest.cvv,
            cardHolderName = creditCardRequest.cardHolderName
        )
    }

    fun toResponse(creditCard: CreditCard): CreditCardResponse{
        return CreditCardResponse(
            number = creditCard.number,
            expirationDate = creditCard.expirationDate,
            cvv = creditCard.cvv,
            cardHolderName = creditCard.cardHolderName
        )
    }

    fun toResponse(creditCards: List<CreditCard>): List<CreditCardResponse>{
        return creditCards.map { toResponse(it) }
    }

}