package com.covidcasesanalysis.country.api

import com.covidcasesanalysis.country.model.MyCountry
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {

    @GET("countries")
    fun getAffectedCountryList () : Call<List<MyCountry>>
}