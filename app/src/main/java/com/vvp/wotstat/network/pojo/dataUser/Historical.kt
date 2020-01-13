package com.vvp.wotstat.network.pojo.dataUser

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Historical {

    @SerializedName("spotted")
    @Expose
    var spotted: Int? = null
    @SerializedName("battles_on_stunning_vehicles")
    @Expose
    var battlesOnStunningVehicles: Int? = null
    @SerializedName("max_xp")
    @Expose
    var maxXp: Int? = null
    @SerializedName("avg_damage_blocked")
    @Expose
    var avgDamageBlocked: Float? = null
    @SerializedName("direct_hits_received")
    @Expose
    var directHitsReceived: Int? = null
    @SerializedName("explosion_hits")
    @Expose
    var explosionHits: Int? = null
    @SerializedName("piercings_received")
    @Expose
    var piercingsReceived: Int? = null
    @SerializedName("piercings")
    @Expose
    var piercings: Int? = null
    @SerializedName("max_damage_tank_id")
    @Expose
    var maxDamageTankId: Int? = null
    @SerializedName("xp")
    @Expose
    var xp: Int? = null
    @SerializedName("survived_battles")
    @Expose
    var survivedBattles: Int? = null
    @SerializedName("dropped_capture_points")
    @Expose
    var droppedCapturePoints: Int? = null
    @SerializedName("hits_percents")
    @Expose
    var hitsPercents: Int? = null
    @SerializedName("draws")
    @Expose
    var draws: Int? = null
    @SerializedName("max_xp_tank_id")
    @Expose
    var maxXpTankId: Int? = null
    @SerializedName("battles")
    @Expose
    var battles: Int? = null
    @SerializedName("damage_received")
    @Expose
    var damageReceived: Int? = null
    @SerializedName("avg_damage_assisted")
    @Expose
    var avgDamageAssisted: Float? = null
    @SerializedName("max_frags_tank_id")
    @Expose
    var maxFragsTankId: Any? = null
    @SerializedName("avg_damage_assisted_track")
    @Expose
    var avgDamageAssistedTrack: Float? = null
    @SerializedName("frags")
    @Expose
    var frags: Int? = null
    @SerializedName("stun_number")
    @Expose
    var stunNumber: Int? = null
    @SerializedName("avg_damage_assisted_radio")
    @Expose
    var avgDamageAssistedRadio: Float? = null
    @SerializedName("capture_points")
    @Expose
    var capturePoints: Int? = null
    @SerializedName("stun_assisted_damage")
    @Expose
    var stunAssistedDamage: Int? = null
    @SerializedName("max_damage")
    @Expose
    var maxDamage: Int? = null
    @SerializedName("hits")
    @Expose
    var hits: Int? = null
    @SerializedName("battle_avg_xp")
    @Expose
    var battleAvgXp: Int? = null
    @SerializedName("wins")
    @Expose
    var wins: Int? = null
    @SerializedName("losses")
    @Expose
    var losses: Int? = null
    @SerializedName("damage_dealt")
    @Expose
    var damageDealt: Int? = null
    @SerializedName("no_damage_direct_hits_received")
    @Expose
    var noDamageDirectHitsReceived: Int? = null
    @SerializedName("max_frags")
    @Expose
    var maxFrags: Int? = null
    @SerializedName("shots")
    @Expose
    var shots: Int? = null
    @SerializedName("explosion_hits_received")
    @Expose
    var explosionHitsReceived: Int? = null
    @SerializedName("tanking_factor")
    @Expose
    var tankingFactor: Float? = null
}