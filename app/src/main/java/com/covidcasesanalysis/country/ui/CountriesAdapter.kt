package com.covidcasesanalysis.country.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidcasesanalysis.R
import com.squareup.picasso.Picasso
import com.covidcasesanalysis.country.model.MyCountry

class CountriesAdapter(
    private val countriesList: List<MyCountry>,
    private val clickListener: (MyCountry) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_row_item, parent, false)
        return ViewHolder(view) {
            clickListener(countriesList[it])
        }
    }


    override fun getItemCount(): Int {

        return countriesList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${countriesList.size} ")


        return holder.bind(countriesList[position])

    }

    class ViewHolder(itemView: View, clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        var imageViewCountryFlag: ImageView = itemView.findViewById(R.id.ivCountryFlag)
        var textViewCountryName: TextView = itemView.findViewById(R.id.tvCountryName)

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(country: MyCountry) {
            textViewCountryName.text = country.country
            Picasso.with(itemView.context).load(country.countryInfo.flag)
                .into(imageViewCountryFlag)
        }
    }
}