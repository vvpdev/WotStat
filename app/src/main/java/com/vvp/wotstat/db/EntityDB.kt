package com.vvp.wotstat.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityDB (

    @PrimaryKey
    var searchText: String = ""
)