package com.vvp.wotstat.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RequestTextModel (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val requestText: String
)