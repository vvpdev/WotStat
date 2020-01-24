package com.vvp.wotstat.model

import android.os.Parcel
import android.os.Parcelable

data class Player(


    //Parcelable - тип для передачи кастомного класса через bundle

    var account_id: Int = 0,
    var nickname: String = "",
    var statistics: Double = 0.0,
    var globalRating: Int = 0


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(account_id)
        parcel.writeString(nickname)
        parcel.writeDouble(statistics)
        parcel.writeInt(globalRating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}








