package com.getlose.myhiskiocourse.CourseOne

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.getlose.myhiskiocourse.Adapter.WeatherAdapter
import com.getlose.myhiskiocourse.Interfaces.IRetrofitCallBack
import com.getlose.myhiskiocourse.Interfaces.IWeatherService
import com.getlose.myhiskiocourse.Librarys.OkhttpClientManager
import com.getlose.myhiskiocourse.Models.WeatherResponseModel
import com.getlose.myhiskiocourse.databinding.ActivitySevenTeenThreeActivityBinding
import retrofit2.Call
import retrofit2.Response

class SevenTeenThreeActivity : BaseActivity() {

    private val TAG = "SevenTeenThreeActivity"
    private lateinit var binding : ActivitySevenTeenThreeActivityBinding
    private lateinit var adapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySevenTeenThreeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = WeatherAdapter()
        binding.recyclerviewWeather.adapter = adapter
        binding.recyclerviewWeather.layoutManager =LinearLayoutManager(this)

        getWeatherList()
    }

    //weather api
    private fun getWeatherList() {
        val weatherService = OkhttpClientManager.client.create(IWeatherService::class.java)
        weatherService.getWeathers().enqueue(retrofit2Callback(weatherCallback,binding.includeBaseProgressbarOverlay.baseLoadingProgressBar))
    }

    //api callback
    private val weatherCallback = object : IRetrofitCallBack<WeatherResponseModel> {
        override fun onResponse(
            call: Call<WeatherResponseModel>,
            response: Response<WeatherResponseModel>
        ) {
            if(response.body()?.success == true){
                //Log.d(TAG, "onResponse: body => ${response.body()?.records?.location}")
                adapter.updateItems(response.body()?.records?.location)
                adapter.notifyDataSetChanged()
            }
        }

        override fun onFailure(call: Call<WeatherResponseModel>, t: Throwable) {
            Log.d(TAG, "onFailure: $t")
        }
    }
}
