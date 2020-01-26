package com.vvp.wotstat.di.modules

import android.content.Context
import androidx.room.Room
import com.vvp.wotstat.App
import com.vvp.wotstat.db.DateBase
import com.vvp.wotstat.db.MethodsDAO
import dagger.Module
import dagger.Provides


@Module
class DBModule {


    @Provides
    fun provideContext(): Context{
        return App.context!!
    }


    @Provides
    fun provideDateBase(): DateBase {
        return Room.databaseBuilder(provideContext(), DateBase::class.java, "playersDB").build()
    }


    @Provides
    fun provideDao(dateBase: DateBase): MethodsDAO {
        return dateBase.methodsDao()
    }


}