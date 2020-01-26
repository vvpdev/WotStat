package com.vvp.wotstat.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.wotstat.App
import com.vvp.wotstat.db.EntityDB
import com.vvp.wotstat.db.MethodsDAO
import com.vvp.wotstat.providers.DataProvider
import com.vvp.wotstat.views.SearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@InjectViewState
class SearchPresenter: MvpPresenter<SearchView>() {

    @Inject
    lateinit var provider: DataProvider

    @Inject
    lateinit var methodsDAO: MethodsDAO


    override fun attachView(view: SearchView?) {
        super.attachView(view)

        App.diComponent!!.injectSearchPresenter(this)
    }

    // загрузка списка игроков
    suspend fun loadListPlayers(searchText: String)  {

        // запись значения поиска в БД
        insertPlayerToDB(newNickname = searchText)


        viewState.showProgress(true)

        CoroutineScope(Dispatchers.IO).launch {

            // загрузка id игроков
            val listFromSearch = provider.getIdUser(searchText).await()

            // загрузка статистики для каждого найденного игрока
            listFromSearch.forEach {

                val stat = provider.getStatUser(it.account_id).await()
                it.statistics = stat!!
            }

            // обновление UI
            CoroutineScope(Dispatchers.Main).launch {

                if (listFromSearch.isNullOrEmpty()){
                    viewState.showProgress(false)
                    viewState.updateData(players = listFromSearch)      // кидаем пустой массив
                    viewState.showMessage("такого игрока нет")
                }
                else{
                    viewState.showProgress(false)
                    viewState.updateData(players = listFromSearch)
                }
            }
        }
    }



    // запись запроса в БД
    private fun insertPlayerToDB(newNickname: String){

        val newEntityDB = EntityDB()
        newEntityDB.searchText = newNickname

        CoroutineScope(Dispatchers.IO).launch {
            methodsDAO.insertPlayer(newEntityDB)
        }
    }

}