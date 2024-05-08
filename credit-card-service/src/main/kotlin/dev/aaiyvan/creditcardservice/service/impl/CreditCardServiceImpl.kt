package dev.aaiyvan.creditcardservice.service.impl

import dev.aaiyvan.creditcardservice.exception.EntityNotFoundException
import dev.aaiyvan.creditcardservice.mapper.CreditCardMapper
import dev.aaiyvan.creditcardservice.model.dto.CreditCardRequest
import dev.aaiyvan.creditcardservice.model.dto.CreditCardResponse
import dev.aaiyvan.creditcardservice.model.entity.CreditCard
import dev.aaiyvan.creditcardservice.repository.CreditCardRepository
import dev.aaiyvan.creditcardservice.service.CreditCardService
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
class CreditCardServiceImpl(
    private var creditCardRepository: CreditCardRepository,
    private var creditCardMapper: CreditCardMapper
) : CreditCardService {

    override fun createCreditCard(creditCardRequest: CreditCardRequest): CreditCardResponse {
        val creditCard = creditCardMapper.toCreditCard(creditCardRequest)
        creditCardRepository.save(creditCard)
        println("Saving card with id: ${creditCard.id}")

        return creditCardMapper.toResponse(creditCard)
    }

    override fun deleteCreditCard(creditCardId: String) {
        creditCardRepository.deleteById(creditCardId)
    }

    override fun getAllCreditCards(): List<CreditCardResponse> {
        return creditCardMapper.toResponse(creditCardRepository.findAll())
    }

    override fun getInfoCreditCard(creditCardId: String): CreditCardResponse {
        return creditCardMapper.toResponse(getCreditCard(creditCardId))
    }

    override fun getCreditCard(creditCardId: String): CreditCard {
        return creditCardRepository.findById(creditCardId)
            .orElseThrow{EntityNotFoundException(CreditCard::class.java, creditCardId)}
    }

}