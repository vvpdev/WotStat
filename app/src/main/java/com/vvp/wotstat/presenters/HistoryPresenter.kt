package com.vvp.wotstat.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.wotstat.App
import com.vvp.wotstat.db.EntityDB
import com.vvp.wotstat.db.MethodsDAO
import com.vvp.wotstat.views.HistoryView
import kotlinx.coroutines.*
import javax.inject.Inject


@InjectViewState
class HistoryPresenter: MvpPresenter<HistoryView>() {

    @Inject
    lateinit var methodsDAO: MethodsDAO


    override fun attachView(view: HistoryView?) {
        super.attachView(view)

        App.diComponent!!.injectHistoryPresenter(this)

        view!!.setupRecyclerView()
    }


    // отправка загруженного списка ко View
    suspend fun sendDataToView(){

        CoroutineScope(Dispatchers.IO).launch {

            val arrayList: ArrayList<EntityDB> = loadListPlayersFromDB().await()

                CoroutineScope(Dispatchers.Main).launch {

                    viewState.showHistoryList(arrayList)
                }
        }
    }


    // загрузка из БД
    private fun loadListPlayersFromDB(): Deferred<ArrayList<EntityDB>>{

        return CoroutineScope(Dispatchers.IO).async {

            val playersList: ArrayList<EntityDB>  = ArrayList()

                playersList.addAll(methodsDAO.getAllPlayers())

            return@async playersList
        }
    }


    fun deleteAll(){
        CoroutineScope(Dispatchers.IO).launch {
            methodsDAO.deleteAllPlayers()
        }
    }


}