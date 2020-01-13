package com.vvp.wotstat.network.pojo.dataUser

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Model {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null
}