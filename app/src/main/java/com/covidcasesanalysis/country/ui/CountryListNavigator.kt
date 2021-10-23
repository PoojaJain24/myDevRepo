package com.covidcasesanalysis.country.ui

import android.content.Context
import android.content.Intent
import com.covidcasesanalysis.country.model.MyCountry
import org.jetbrains.annotations.NotNull

class CountryListNavigator : CountryListContract.Navigator {
    override fun startCountryDataActivity(@NotNull context: Context, country: MyCountry) {
        val intent: Intent = CountryCasesDetail.newIntent(context, country)
        context.startActivity(intent)
    }
}