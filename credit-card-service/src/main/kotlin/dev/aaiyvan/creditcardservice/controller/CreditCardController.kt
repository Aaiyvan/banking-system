package dev.aaiyvan.creditcardservice.controller

import dev.aaiyvan.creditcardservice.model.dto.CreditCardRequest
import dev.aaiyvan.creditcardservice.service.CreditCardService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/credit-cards")
class CreditCardController(
    private var creditCardService: CreditCardService
) {
    @GetMapping
    fun getAllCreditCards() = creditCardService.getAllCreditCards()

    @GetMapping("/info/{creditCardId}")
    fun getInfoCreditCard(
        @PathVariable creditCardId: String
    ) = creditCardService.getInfoCreditCard(creditCardId)

    @PostMapping("/create")
    fun createCreditCard(
        @RequestBody creditCardRequest: CreditCardRequest
    ) = creditCardService.createCreditCard(creditCardRequest)

    @DeleteMapping("/block/{creditCardId}")
    fun deleteCreditCard(
        @PathVariable creditCardId: String
    ) {
        creditCardService.deleteCreditCard(creditCardId)
    }

}