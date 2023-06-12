package com.zym.zymresseler.conn

import com.gm.mvies.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    val api : Api by lazy {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        val gson = gsonBuilder.create()

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor {
                    chain ->
                chain.proceed(chain.request().newBuilder().addHeader("Authorization", "Bearer  eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OTJhMTY3ZDc0N2U4ZTg3YTdiZGVjYTc3ZmUwMzZlMyIsInN1YiI6IjY0ODZjMTU5YzAzNDhiMDBhZWQ1MjUxOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.CZ9eecF026UZaKfMKU73ifDWubsi03rLB3jSlj4lpEI").also {

                }.build())
            }.also { client ->
                if(BuildConfig.DEBUG){
                    val logging= HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api::class.java)
    }
}