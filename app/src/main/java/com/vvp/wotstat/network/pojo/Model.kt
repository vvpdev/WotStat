package com.vvp.wotstat.network.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vvp.wotstat.network.pojo.Data
import com.vvp.wotstat.network.pojo.Error
import com.vvp.wotstat.network.pojo.Meta


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