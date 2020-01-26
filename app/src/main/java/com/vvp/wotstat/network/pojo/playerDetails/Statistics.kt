package com.vvp.wotstat.network.pojo.playerDetails

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Statistics {

    @SerializedName("all")
    @Expose
    var all: All? = null
}