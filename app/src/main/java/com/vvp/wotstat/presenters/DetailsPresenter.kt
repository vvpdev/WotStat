package com.vvp.wotstat.presenters

import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.wotstat.model.Player
import com.vvp.wotstat.providers.DataProvider
import com.vvp.wotstat.views.DetailsView
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@InjectViewState
class DetailsPresenter: MvpPresenter<DetailsView>() {


    private val provider: DataProvider = DataProvider()


    private suspend fun getDataPlayer(account_id: Int){


//        CoroutineScope(Dispatchers.IO).launch {
//
//            val globalRating = provider.getDataUserAsync(account_id = player!!.account_id).await()
//            player.globalRating = globalRating!!
//
//            // update UI
//            CoroutineScope(Dispatchers.Main).launch {
//
//                if (globalRating.isNullOrEmpty()){
//
//                    Toast.makeText(activity?.applicationContext, "ошибка", Toast.LENGTH_SHORT).show()
//                }
//                else{
//
//                    textNickname.text = player.nickname
//                    textRating.text = player.globalRating
//                }
//            }
//        }
    }


}