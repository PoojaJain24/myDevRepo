package com.covidcasesanalysis.country.ui

import com.covidcasesanalysis.country.api.APIClient
import com.covidcasesanalysis.country.api.CountryService
import com.covidcasesanalysis.country.model.MyCountry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryListPresenter(
    private var view: CountryListContract.View,
    private var navigator: CountryListContract.Navigator
) : CountryListContract.Presenter {

    override fun fetchCountryData() {
        view.hideFetchCountryButton()
        view.showProgress()
        val countryService = APIClient.buildService(CountryService::class.java)
        val requestCall = countryService.getAffectedCountryList()
        requestCall.enqueue(object : Callback<List<MyCountry>> {
            override fun onResponse(
                call: Call<List<MyCountry>>,
                response: Response<List<MyCountry>>
            ) {
                if (response.isSuccessful) {
                    view.hideProgress()
                    response.body()?.let { view.showCountryList(it) }

                } else {
                    view.hideProgress()
                    view.showApiErrorMessage(response.message())
                }
            }

            override fun onFailure(call: Call<List<MyCountry>>, t: Throwable) {
                view.showErrorMessage()
            }
        })
    }

    override fun onCountryItemClicked(country: MyCountry) {
        navigator.startCountryDataActivity(view.getViewContext(), country)
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }
}