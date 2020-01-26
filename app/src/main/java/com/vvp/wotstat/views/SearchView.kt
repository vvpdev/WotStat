package com.vvp.wotstat.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vvp.wotstat.network.model.Player


@StateStrategyType(value = AddToEndStrategy::class)        
interface SearchView: MvpView {

    fun showProgress(show: Boolean)

    fun updateData(players: ArrayList<Player>)

    @StateStrategyType(value = SkipStrategy::class)
    fun showMessage(message: String)

    @StateStrategyType(value = SkipStrategy::class)
    fun showMessage(message: Int)

    fun enterTextForSearch()

}
