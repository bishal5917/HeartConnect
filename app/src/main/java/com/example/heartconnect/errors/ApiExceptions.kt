package com.example.heartconnect.errors

open class ApiExceptions(
    val msg: String? = null, val prefix: String? = null, val url: String? = null,
    val statusCode: Int? = null,
) : Exception()

class NotLoggedInException(message: String?) : ApiExceptions(message)

class BadRequestException(message: String? = null, url: String? = null) :
    ApiExceptions(message, "Bad Request", url)

class FetchDataException(message: String? = null, url: String? = null) :
    ApiExceptions(message, "Unable to process", url)

class ApiNotRespondingException(message: String? = null, url: String? = null) :
    ApiExceptions(message, "Api not responded in time", url)

class UnAuthorizedException(message: String? = null, url: String? = null) :
    ApiExceptions(message, "UnAuthorized request", url)
