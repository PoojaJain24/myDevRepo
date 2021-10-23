package com.covidcasesanalysis.country.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val BASE_URL = "https://disease.sh/v2/"
    private val okHttp = OkHttpClient.Builder()
    private val builder = Retrofit.Builder().baseUrl(APIClient.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(APIClient.okHttp.build())
    private val retrofit = APIClient.builder.build()
    fun <T> buildService(serviceType: Class<T>): T {
        return APIClient.retrofit.create(serviceType)
    }
}
