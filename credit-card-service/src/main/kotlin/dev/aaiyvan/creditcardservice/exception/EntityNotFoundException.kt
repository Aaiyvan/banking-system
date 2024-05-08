package dev.aaiyvan.creditcardservice.exception

class EntityNotFoundException(entityClass: Class<*>, id: String) : RuntimeException(
    "Entity - ${entityClass.simpleName} with id '$id' not found."
)