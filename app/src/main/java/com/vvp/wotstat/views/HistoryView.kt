package com.vvp.wotstat.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vvp.wotstat.db.EntityDB

@StateStrategyType(value = AddToEndStrategy::class)
interface HistoryView: MvpView {

    fun setupRecyclerView()

    fun showHistoryList(arrayList: ArrayList<EntityDB>)

    fun showAlertDialog()

}