package com.vvp.wotstat.di.components

import com.vvp.wotstat.di.modules.DBModule
import com.vvp.wotstat.di.modules.DataProviderModule
import com.vvp.wotstat.di.modules.RetrofitFactoryModule
import com.vvp.wotstat.presenters.DetailsPresenter
import com.vvp.wotstat.presenters.HistoryPresenter
import com.vvp.wotstat.presenters.SearchPresenter
import com.vvp.wotstat.providers.DataProvider
import dagger.Component


@Component(modules = [DBModule::class, DataProviderModule::class, RetrofitFactoryModule::class])
interface DIComponent {

    // инжектирование всех переменных
    fun injectSearchPresenter(presenter: SearchPresenter)

    fun injectHistoryPresenter(presenter: HistoryPresenter)

    fun injectDetailsPresenter(presenter: DetailsPresenter)

    fun injectDataProvider(provider: DataProvider)

}