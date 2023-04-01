package com.orafaelsc.fuzecodechallenge.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiCallInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = try {
        chain.proceed(
            chain.request().newBuilder().addHeader(
                name = "x-access-token",
                value = "YAqDjdon0cKQkfEPOyZrOB8wneXfznhnek8dpBH8g39nmK_QunU",
            ).build(),
        )
    } catch (e: IOException) {
        throw e
    }
}
