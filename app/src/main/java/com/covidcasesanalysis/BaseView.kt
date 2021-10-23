package com.covidcasesanalysis

import android.content.Context

interface BaseView <T> {
    fun getViewContext(): Context
    fun setPresenter(presenter : T)
    fun showProgress()
    fun hideProgress()
}