package com.eunice.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * Created by {EUNICE BAKARE T.} on {7/31/23}
 * Email: {eunice@reach.africa}
 */

val moshi: Moshi = Moshi.Builder()
 .add(KotlinJsonAdapterFactory())
 .build()