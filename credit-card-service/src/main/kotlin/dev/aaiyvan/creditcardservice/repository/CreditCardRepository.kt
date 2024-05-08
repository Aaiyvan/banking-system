package dev.aaiyvan.creditcardservice.repository

import dev.aaiyvan.creditcardservice.model.entity.CreditCard
import org.springframework.data.mongodb.repository.MongoRepository

interface CreditCardRepository: MongoRepository<CreditCard, String> {
}