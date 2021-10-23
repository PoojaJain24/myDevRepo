package com.covidcasesanalysis.country.ui

import android.content.Context
import com.covidcasesanalysis.BasePresenter
import com.covidcasesanalysis.BaseView
import com.covidcasesanalysis.country.model.MyCountry

interface CountryListContract {
    interface Presenter : BasePresenter {
        fun fetchCountryData()
        fun onCountryItemClicked(country: MyCountry)
    }

    interface View : BaseView<Presenter> {
        fun showCountryList(countryList: List<MyCountry>)
        fun showApiErrorMessage(errorMessage: String)
        fun showErrorMessage()
        fun hideFetchCountryButton()
    }

    interface Navigator {
        fun startCountryDataActivity(context: Context, country: MyCountry)
    }
}