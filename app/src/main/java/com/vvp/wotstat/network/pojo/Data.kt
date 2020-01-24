package com.vvp.wotstat.network.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Data {

    @SerializedName("nickname")
    @Expose
    var nickname: String? = null
    @SerializedName("account_id")
    @Expose
    var accountId: Int? = null

}