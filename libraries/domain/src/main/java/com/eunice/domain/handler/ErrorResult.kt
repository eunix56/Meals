package com.eunice.domain.handler

/**
 * Created by EUNICE BAKARE T. on 12/03/2022
 * Email: eunice@reach.africa
 */

//Gotten from https://proandroiddev.com/android-error-handling-in-clean-architecture-844a7fc0dc03
sealed class ErrorResult {

    object Network : ErrorResult()

    object NotFound : ErrorResult()

    object AccessDenied : ErrorResult()

    object ServiceUnavailable : ErrorResult()

    object Unknown : ErrorResult()
}
