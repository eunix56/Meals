package com.eunice.view

import com.eunice.domain.handler.ErrorResult

/**
 * Created by EUNICE BAKARE T. on 26/04/2022
 * Email: eunice@reach.africa
 */

fun getErrorResponse(error: ErrorResult): String {
    val errorResult =  when (error) {
        is ErrorResult.Network -> "Network Error."
        is ErrorResult.ServiceUnavailable -> "Service is not available."
        is ErrorResult.AccessDenied -> "Access denied."
        is ErrorResult.NotFound -> "Data not found."
        else -> "Unknown error."
    }

    return errorResult.plus(" Please try again :)")
}