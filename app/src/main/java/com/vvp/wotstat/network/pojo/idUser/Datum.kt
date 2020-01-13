package com.vvp.wotstat.network.pojo.idUser

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Datum {

    @SerializedName("nickname")
    @Expose
    var nickname: String? = null
    @SerializedName("account_id")
    @Expose
    var accountId: Int? = null
}