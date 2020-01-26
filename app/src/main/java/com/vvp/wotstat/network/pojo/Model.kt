package com.vvp.wotstat.network.pojo

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
    var data: List<Data>? = null
    @SerializedName("error")
    @Expose
    var error: Error? = null

}