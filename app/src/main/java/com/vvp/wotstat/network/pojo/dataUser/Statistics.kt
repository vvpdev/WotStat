package com.vvp.wotstat.network.pojo.dataUser

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Statistics {

    @SerializedName("clan")
    @Expose
    var clan: Clan? = null
    @SerializedName("all")
    @Expose
    var all: All? = null
    @SerializedName("regular_team")
    @Expose
    var regularTeam: RegularTeam? = null
    @SerializedName("trees_cut")
    @Expose
    var treesCut: Int? = null
    @SerializedName("company")
    @Expose
    var company: Company? = null
    @SerializedName("stronghold_skirmish")
    @Expose
    var strongholdSkirmish: StrongholdSkirmish? = null
    @SerializedName("stronghold_defense")
    @Expose
    var strongholdDefense: StrongholdDefense? = null
    @SerializedName("historical")
    @Expose
    var historical: Historical? = null
    @SerializedName("team")
    @Expose
    var team: Team? = null
    @SerializedName("frags")
    @Expose
    var frags: Any? = null
}