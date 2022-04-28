package com.example.domain.handler

/**
 * Created by EUNICE BAKARE T. on 12/03/2022
 * Email: eunice@reach.africa
 */
sealed class DataResult<T> {
    object Loading : DataResult<Nothing>()

    data class Success<T>(val data: T) : DataResult<T>()

    data class Error<T>(val error: ErrorResult) : DataResult<T>()
}
