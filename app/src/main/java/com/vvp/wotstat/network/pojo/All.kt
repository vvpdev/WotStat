package com.vvp.wotstat.network.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class All {

    @SerializedName("wins")
    @Expose
    var wins: Int? = null
    @SerializedName("battles")
    @Expose
    var battles: Int? = null

}