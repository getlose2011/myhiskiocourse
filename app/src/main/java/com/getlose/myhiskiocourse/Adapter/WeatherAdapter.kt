package com.getlose.myhiskiocourse.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getlose.myhiskiocourse.Models.Location
import com.getlose.myhiskiocourse.databinding.RowItemWeatherLayoutBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    var items: List<Location>? = null

    fun updateItems(items: List<Location>?){
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = RowItemWeatherLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        if (holder is WeatherViewHolder) {
            holder.bindData(items!![position])
        }
    }

    override fun getItemCount(): Int {
        return items?.count() ?: 0
    }

    inner class WeatherViewHolder(val viewBinding: RowItemWeatherLayoutBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(item: Location){
            val wx = item.weatherElement?.find { it.elementName == "Wx" }?.time?.first()?.parameter?.parameterName
            val pop = item.weatherElement?.find { it.elementName == "PoP" }?.time?.first()?.parameter?.parameterName
            val minT = item.weatherElement?.find { it.elementName == "MinT" }?.time?.first()?.parameter?.parameterName
            val maxT = item.weatherElement?.find { it.elementName == "MaxT" }?.time?.first()?.parameter?.parameterName
            val ci = item.weatherElement?.find { it.elementName == "CI" }?.time?.first()?.parameter?.parameterName

            viewBinding.location.text = item.locationName
            viewBinding.ci.text = ci
            viewBinding.pop.text = "降雨機率:$pop%"
            viewBinding.minT.text = "$minT °C"
            viewBinding.maxT.text = "$maxT °C"
            viewBinding.wx.text = wx
        }
    }


}