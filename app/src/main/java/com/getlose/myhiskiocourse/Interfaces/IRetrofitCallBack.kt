package com.getlose.myhiskiocourse.Interfaces

import retrofit2.Call
import retrofit2.Response


interface IRetrofitCallBack<T> {
    fun onResponse(
        call: Call<T>,
        response: Response<T>
    )
    fun onFailure(call: Call<T>, t: Throwable)
}