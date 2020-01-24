package com.vvp.wotstat.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAO {

    @Query("SELECT * FROM RequestTextModel")
    fun getAllRequest(): ArrayList<RequestTextModel>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun writeTextRequest(textRequest: RequestTextModel)



}