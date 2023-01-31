package com.getlose.myhiskiocourse.Models

import com.google.gson.annotations.SerializedName



data class InvAppResponseModel(
    val msg: String,
    val updateDate: String,
    val code: String,
    val firstPrizeAmt: String,
    val superPrizeNo: String,
    val firstPrizeNo1: String,
    val firstPrizeNo2: String,
    val firstPrizeNo3: String,
    val spcPrizeAmt: String,
    val firstPrizeNo4: String,
    val fifthPrizeAmt: String
)
