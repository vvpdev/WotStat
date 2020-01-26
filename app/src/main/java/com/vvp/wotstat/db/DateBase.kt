package com.vvp.wotstat.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [EntityDB::class], version = 1, exportSchema = false)
abstract class DateBase: RoomDatabase() {

    abstract fun methodsDao(): MethodsDAO
}