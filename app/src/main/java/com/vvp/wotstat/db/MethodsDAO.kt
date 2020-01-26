package com.vvp.wotstat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MethodsDAO {

    @Query("SELECT * FROM EntityDB")
    fun getAllPlayers(): List<EntityDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(entityDB: EntityDB)

    @Query("DELETE FROM EntityDB")
    fun deleteAllPlayers()
}