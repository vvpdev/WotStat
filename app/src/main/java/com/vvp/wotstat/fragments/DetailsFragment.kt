package com.vvp.wotstat.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vvp.wotstat.R
import com.vvp.wotstat.network.model.Player
import com.vvp.wotstat.network.pojo.playerDetails.PlayerDetails
import com.vvp.wotstat.presenters.DetailsPresenter
import com.vvp.wotstat.views.DetailsView
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailsFragment : MvpAppCompatFragment(), DetailsView {


    @InjectPresenter
    lateinit var presenter: DetailsPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



    override fun sendIdToPresenter() {

        val selectedPlayer: Player? = arguments!!.getParcelable("selectedPlayer")

        // nickname - fragment title
        activity!!.title = selectedPlayer!!.nickname

        CoroutineScope(Dispatchers.Main).launch {  presenter.getPlayerData(selectedPlayer)  }
    }



    override fun showProgress(show: Boolean) {

        if (show){

            // progress
            progressLoadingPlayerDetails.visibility = View.VISIBLE

            // header
            textGlobalRating.visibility = View.GONE
            textValueGlobalRating.visibility = View.GONE

            // block 1
            textWinRate.visibility = View.GONE
            textValueWinRate.visibility = View.GONE
            textBattles.visibility = View.GONE
            textValueBattles.visibility = View.GONE

            // block 2
            textMaxFrags.visibility = View.GONE
            textValueMaxFrags.visibility = View.GONE
            textMaxXP.visibility = View.GONE
            textValueMaxXP.visibility = View.GONE

            // block 3
            textBattleAvgXp.visibility = View.GONE
            textValueBattleAvgXp.visibility = View.GONE
            textAvgDamageBlocked.visibility = View.GONE
            textValueAvgDamageBlocked.visibility = View.GONE

        }
        else{
            // progress
            progressLoadingPlayerDetails.visibility = View.GONE

            // header
            textGlobalRating.visibility = View.VISIBLE
            textValueGlobalRating.visibility = View.VISIBLE

            // block 1
            textWinRate.visibility = View.VISIBLE
            textValueWinRate.visibility = View.VISIBLE
            textBattles.visibility = View.VISIBLE
            textValueBattles.visibility = View.VISIBLE

            // block 2
            textMaxFrags.visibility = View.VISIBLE
            textValueMaxFrags.visibility = View.VISIBLE
            textMaxXP.visibility = View.VISIBLE
            textValueMaxXP.visibility = View.VISIBLE

            // block 3
            textBattleAvgXp.visibility = View.VISIBLE
            textValueBattleAvgXp.visibility = View.VISIBLE
            textAvgDamageBlocked.visibility = View.VISIBLE
            textValueAvgDamageBlocked.visibility = View.VISIBLE
        }
    }


    @SuppressLint("SetTextI18n")
    override fun showPlayerDetails(playerDetails: PlayerDetails, selectedPlayer: Player?) {

        //header
        textValueGlobalRating.text = playerDetails.globalRating.toString()

        //block 1
        textValueWinRate.text = selectedPlayer!!.statistics.toString() + "%"
        textValueBattles.text = playerDetails.statistics!!.all!!.battles.toString()

        //block 2
        textValueMaxFrags.text = playerDetails.statistics!!.all!!.maxFrags.toString()
        textValueMaxXP.text = playerDetails.statistics!!.all!!.maxXp.toString()

        //block 3
        textValueBattleAvgXp.text = playerDetails.statistics!!.all!!.battleAvgXp.toString()
        textValueAvgDamageBlocked.text = playerDetails.statistics!!.all!!.avgDamageBlocked.toString()
    }

}
