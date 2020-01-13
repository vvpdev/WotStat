package com.vvp.wotstat.network.pojo.idUser

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Error {

    @SerializedName("field")
    @Expose
    var field: String? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("code")
    @Expose
    var code: Int? = null
    @SerializedName("value")
    @Expose
    var value: String? = null


}