package com.vvp.wotstat.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.wotstat.App
import com.vvp.wotstat.network.model.Player
import com.vvp.wotstat.network.pojo.playerDetails.PlayerDetails
import com.vvp.wotstat.providers.DataProvider
import com.vvp.wotstat.views.DetailsView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@InjectViewState
class DetailsPresenter: MvpPresenter<DetailsView>() {

    @Inject
    lateinit var provider: DataProvider


    override fun attachView(view: DetailsView?) {
        super.attachView(view)

        App.diComponent!!.injectDetailsPresenter(this)
    }


    init {
        viewState.sendIdToPresenter()
    }



    suspend fun getPlayerData(selectedPlayer: Player){

        viewState.showProgress(true)

        CoroutineScope(Dispatchers.IO).launch {

            val playerDetails: PlayerDetails = provider.getPlayerData(selectedPlayer.account_id).await()

            CoroutineScope(Dispatchers.Main).launch {

                viewState.showProgress(false)
                viewState.showPlayerDetails(playerDetails, selectedPlayer)
            }
        }
    }


}