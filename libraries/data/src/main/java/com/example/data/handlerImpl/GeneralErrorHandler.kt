package com.example.data.handlerImpl

import com.example.domain.handler.ErrorHandler
import com.example.domain.handler.ErrorResult
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

/**
 * Created by EUNICE BAKARE T. on 12/03/2022
 * Email: eunice@reach.africa
 */

//https://proandroiddev.com/android-error-handling-in-clean-architecture-844a7fc0dc03
class GeneralErrorHandler: ErrorHandler {

    override fun getError(throwable: Throwable): ErrorResult {
        return when(throwable) {
            is IOException -> ErrorResult.Network
            is HttpException -> {
                when(throwable.code()) {
                    // no cache found in case of no network, thrown by retrofit -> treated as network error
                    HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> ErrorResult.Network

                    // not found
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorResult.NotFound

                    // access denied
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorResult.AccessDenied

                    // unavailable service
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorResult.ServiceUnavailable

                    // all the others will be treated as unknown error
                    else -> ErrorResult.Unknown
                }
            }
            else -> ErrorResult.Unknown
        }
    }
}