package com.vvp.wotstat

import android.app.Application
import android.content.Context
import com.vvp.wotstat.di.components.DIComponent
import com.vvp.wotstat.di.components.DaggerDIComponent

class App: Application() {


    companion object{

        var diComponent: DIComponent? = null
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()

        diComponent = DaggerDIComponent.builder().build()
        context = this
    }
}