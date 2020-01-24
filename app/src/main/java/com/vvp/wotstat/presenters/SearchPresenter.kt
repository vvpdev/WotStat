package com.vvp.wotstat.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vvp.wotstat.App
import com.vvp.wotstat.di.components.DIComponent
import com.vvp.wotstat.di.components.DaggerDIComponent
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


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        DaggerDIComponent.create().injectSearchPresenter(this)
    }


    // загрузка списка игроков
    suspend fun loadListPlayers(searchText: String)  {

        viewState.showProgress(true)

        CoroutineScope(Dispatchers.IO).launch {

            // загрузка id игроков
            val listFromSearch = provider.getIdUserAsync(searchText).await()

            // загрузка статистики для каждого найденного игрока
            listFromSearch.forEach {

                val stat = provider.getStatUser(it.account_id).await()
                it.statistics = stat!!
            }


            // обновление UI - корутина для главного потока
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
    fun writeRequestToDB(requestText: String){


    }





}