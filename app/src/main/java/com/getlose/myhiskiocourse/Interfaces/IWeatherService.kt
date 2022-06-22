package com.getlose.myhiskiocourse.Interfaces


import com.getlose.myhiskiocourse.Models.WeatherResponseModel
import retrofit2.Call
import retrofit2.http.GET


interface IWeatherService {
    //取得天氣
    @GET("F-C0032-001")
    fun getWeathers(): Call<WeatherResponseModel>

//    @GET("F-C0032-001")
//    fun getWeathers2(@Query("limit") limit:Int): Call<WeatherResponse>
//
//    @POST("F-C0032-001")
//    fun sendOrder(@Body order:OrderRequest): Call<WeatherResponse>
}
