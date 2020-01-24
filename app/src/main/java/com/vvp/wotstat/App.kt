package com.vvp.wotstat

import android.app.Application
import androidx.room.Room
import com.vvp.wotstat.db.DateBase

class App: Application() {


    companion object{
        lateinit var dateBase: DateBase
    }


    override fun onCreate() {
        super.onCreate()

        dateBase = Room.databaseBuilder(this, DateBase::class.java, "db_Requests").build()
    }

}