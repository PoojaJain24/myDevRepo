package com.covidcasesanalysis.country.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidcasesanalysis.databinding.ActivityMainBinding
import com.covidcasesanalysis.country.model.MyCountry
import com.example.covidcasesanalysis.R

class CountryListActivity : AppCompatActivity(), CountryListContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: CountryListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPresenter(CountryListPresenter(this, CountryListNavigator()))
        setupViews()
    }

    private fun setupViews() {
        binding.fetchCountryButton.setOnClickListener { presenter.fetchCountryData() }
    }

    override fun showCountryList(countryList: List<MyCountry>) {

        binding.countryRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CountryListActivity)
            adapter = CountriesAdapter(countryList) {
                presenter.onCountryItemClicked(it)
            }
        }
        binding.countryRecycler.isVisible = true
    }

    override fun showApiErrorMessage(errorMessage: String) {
        Toast.makeText(
            this@CountryListActivity,
            "Something went wrong $errorMessage",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showErrorMessage() {
        val errorMessage = getString(R.string.error_msg)
        Toast.makeText(
            this@CountryListActivity,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun hideFetchCountryButton() {
        binding.fetchCountryButton.isVisible = false
    }

    override fun getViewContext(): Context {
        return this
    }

    override fun setPresenter(presenter: CountryListContract.Presenter) {
        this.presenter = presenter
    }

    override fun showProgress() {
        binding.progressBar.isVisible = true
    }

    override fun hideProgress() {
        binding.progressBar.isVisible = false
    }
}