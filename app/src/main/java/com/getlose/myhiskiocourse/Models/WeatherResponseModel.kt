package com.getlose.myhiskiocourse.Models

import com.google.gson.annotations.SerializedName



data class WeatherResponseModel(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("result")
    val result: Result,
    @SerializedName("records")
    val records: Records
)

data class Result(
    @SerializedName("resource_id")
    val resourceId: String,
    @SerializedName("fields")
    val fields: List<Field>
)

data class Records(
    @SerializedName("datasetDescription")
    val datasetDescription: String,
    @SerializedName("location")
    val location: List<Location>
)

data class Field(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String
)

data class Location(
    @SerializedName("locationName")
    val locationName: String,
    @SerializedName("weatherElement")
    val weatherElement: List<WeatherElement>
)

data class WeatherElement(
    @SerializedName("elementName")
    val elementName: String,
    @SerializedName("time")
    val time: List<Time>
)

data class Time(
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("parameter")
    val parameter: Parameter
)

data class Parameter(
    @SerializedName("parameterName")
    val parameterName: String,
    @SerializedName("parameterValue")
    val parameterValue: String,
    @SerializedName("parameterUnit")
    val parameterUnit: String
)