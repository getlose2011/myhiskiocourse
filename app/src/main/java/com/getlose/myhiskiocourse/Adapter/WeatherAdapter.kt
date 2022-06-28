package com.getlose.myhiskiocourse.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.getlose.myhiskiocourse.Models.Location
import com.getlose.myhiskiocourse.databinding.RowItemWeatherLayoutBinding
import com.getlose.myhiskiocourse.databinding.RowItemWeatherOddLayoutBinding
import java.lang.Exception

//用到二個 XML VIEW
class WeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TAG = "WeatherAdapter"
    var items: List<Location>? = null
    private var VIEW_TYPE_ODD = 0
    private var VIEW_TYPE_EVEN = 1

    fun updateItems(items: List<Location>?){
        this.items = items
    }

    override fun getItemViewType(position: Int): Int {
        if (position%2==0){
            return VIEW_TYPE_ODD
        }else{
            return VIEW_TYPE_EVEN
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

    inner class WeatherViewHolder2(val viewBinding: RowItemWeatherOddLayoutBinding) :RecyclerView.ViewHolder(viewBinding.root){

        fun bindData(item: Location){
            val wx = item.weatherElement?.find { it.elementName == "Wx" }?.time?.first()?.parameter?.parameterName
            val pop = item.weatherElement?.find { it.elementName == "PoP" }?.time?.first()?.parameter?.parameterName
            val minT = item.weatherElement?.find { it.elementName == "MinT" }?.time?.first()?.parameter?.parameterName
            val maxT = item.weatherElement?.find { it.elementName == "MaxT" }?.time?.first()?.parameter?.parameterName

            viewBinding.location.text = item.locationName
            viewBinding.pop.text = "降雨機率:$pop%"
            viewBinding.minT.text = "$minT °C"
            viewBinding.maxT.text = "$maxT °C"
            viewBinding.wx.text = wx
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder: $viewType")
        if(viewType == VIEW_TYPE_EVEN){
            val binding = RowItemWeatherLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return WeatherViewHolder(binding)
        }else if ( viewType == VIEW_TYPE_ODD){
            val binding = RowItemWeatherOddLayoutBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return WeatherViewHolder2(binding)
        }
        else{
            throw Exception("Unknown viewType:${viewType}")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items?.get(position)
        if (holder is WeatherViewHolder){
            if (data != null) {
                holder.bindData(data)
            }
        }else if (holder is WeatherViewHolder2){

            if (data != null) {
                holder.bindData(data)
            }
        }else{
            throw Exception("Unknown holder:${holder.javaClass}")
        }
    }


}