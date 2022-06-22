package com.getlose.myhiskiocourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.getlose.myhiskiocourse.Adapter.WeatherAdapter
import com.getlose.myhiskiocourse.Models.WeatherResponseModel
import com.getlose.myhiskiocourse.databinding.ActivitySevenTeenThreeActivityBinding
import com.getlose.myhiskiocourse.interfaces.IWeatherService
import com.getlose.myhiskiocourse.librarys.OkhttpClientManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SevenTeenThreeActivity : AppCompatActivity() {

    private val TAG = "SevenTeenThreeActivity"
    private lateinit var binding : ActivitySevenTeenThreeActivityBinding
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySevenTeenThreeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.includeBaseProgressbarOverlay.baseLoadingProgressBar.visibility = View.VISIBLE
        adapter = WeatherAdapter()
        binding.recyclerviewWeather.adapter = adapter
        binding.recyclerviewWeather.layoutManager =LinearLayoutManager(this)

        getData()
    }

    //weather api
    private fun getData() {

        val weatherService = OkhttpClientManager.client.create(IWeatherService::class.java)
        weatherService.getWeathers().enqueue(object : Callback<WeatherResponseModel>{
            override fun onResponse(
                call: Call<WeatherResponseModel>,
                response: Response<WeatherResponseModel>
            ) {
                if(response.body()?.success == true){
                    //Log.d(TAG, "onResponse: body => ${response.body()?.records?.location}")
                    adapter.updateItems(response.body()?.records?.location)
                    adapter.notifyDataSetChanged()
                    binding.includeBaseProgressbarOverlay.baseLoadingProgressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<WeatherResponseModel>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }
        })
    }

}