package com.vvp.wotstat.network.pojo.playerDetails

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class PlayerDetails {


    @SerializedName("client_language")
    @Expose
    var clientLanguage: String? = null
    @SerializedName("last_battle_time")
    @Expose
    var lastBattleTime: Int? = null
    @SerializedName("account_id")
    @Expose
    var accountId: Int? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: Int? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: Int? = null
    @SerializedName("private")
    @Expose
    var _private: Any? = null
    @SerializedName("global_rating")
    @Expose
    var globalRating: Int? = null
    @SerializedName("clan_id")
    @Expose
    var clanId: Any? = null
    @SerializedName("statistics")
    @Expose
    var statistics: Statistics? = null
    @SerializedName("nickname")
    @Expose
    var nickname: String? = null
    @SerializedName("logout_at")
    @Expose
    var logoutAt: Int? = null


}