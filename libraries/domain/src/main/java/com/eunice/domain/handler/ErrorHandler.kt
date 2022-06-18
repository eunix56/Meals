package com.eunice.domain.handler

/**
 * Created by EUNICE BAKARE T. on 12/03/2022
 * Email: eunice@reach.africa
 */

//Gotten from https://proandroiddev.com/android-error-handling-in-clean-architecture-844a7fc0dc03
interface ErrorHandler {

    fun getError(throwable: Throwable?): ErrorResult
}