package com.kotlinmvvmdemo.webservice

import com.kotlinmvvmdemo.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Colan Infotech.
 */
class WebServiceClient private constructor() {
    companion object {

        val BASE_URL = "https://jsonplaceholder.typicode.com/"


        private var retrofit: Retrofit? = null
        private var builder: Retrofit.Builder? = null
        private var httpClient: OkHttpClient.Builder? = null

        val client: Retrofit
            get() {

                if (builder == null) {
                    builder = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())

                }

                val loggingInterceptor = HttpLoggingInterceptor()
                if (BuildConfig.DEBUG)
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                httpClient = OkHttpClient.Builder()
                httpClient!!.addInterceptor(loggingInterceptor)
                builder!!.client(httpClient!!.build())
                retrofit = builder!!.build()

                return (retrofit as Retrofit?)!!
            }

    }
}
