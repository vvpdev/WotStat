package com.vvp.wotstat.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vvp.wotstat.network.model.Player
import com.vvp.wotstat.network.pojo.playerDetails.PlayerDetails

@StateStrategyType(value = AddToEndStrategy::class)
interface DetailsView: MvpView {

    fun sendIdToPresenter()

    fun showProgress(show: Boolean)

    fun showPlayerDetails(playerDetails: PlayerDetails, selectedPlayer: Player?)

}