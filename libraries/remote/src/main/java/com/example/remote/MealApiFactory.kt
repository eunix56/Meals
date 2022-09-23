package com.example.remote

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by EUNICE BAKARE T. on 04/03/2022
 * Email: eunice@reach.africa
 */

private const val BASE_URL =
    "https://www.themealdb.com/api/json/v1/1/"

object MealApiFactory {

    fun makeApiService(isDebug: Boolean, moshi: Moshi): MealApiService {
        val okHttpClient: OkHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug)
        )
        return makeApiService(okHttpClient, moshi)
    }
    

    private fun makeApiService(okHttpClient: OkHttpClient, moshi: Moshi): MealApiService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(MealApiService::class.java)
    }


    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}