package com.vvp.wotstat.di.components

import com.vvp.wotstat.di.moduls.DataProviderModule
import com.vvp.wotstat.di.moduls.RetrofitModule
import com.vvp.wotstat.presenters.SearchPresenter
import com.vvp.wotstat.providers.DataProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [DataProviderModule::class, RetrofitModule::class])
interface DIComponent {

    fun injectSearchPresenter(presenter: SearchPresenter)

    fun injectDataProvider(provider: DataProvider)

}