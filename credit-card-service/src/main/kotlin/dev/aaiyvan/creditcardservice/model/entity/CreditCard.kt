package dev.aaiyvan.creditcardservice.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Field

class CreditCard(

    @Id
    var id: String? = null,

    @Indexed(unique = true) @Field(name = "c_number")
    var number: String? = null,

    @Field(name = "c_expiration_date")
    var expirationDate: String? = null,

    @Field(name = "c_cvv")
    var cvv: String? = null,

    @Field(name = "c_card_holder_name")
    var cardHolderName: String? = null

)