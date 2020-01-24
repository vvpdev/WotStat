package com.vvp.wotstat.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vvp.wotstat.model.Player

import com.vvp.wotstat.R
import com.vvp.wotstat.adapters.AdapterListPlayers
import com.vvp.wotstat.presenters.SearchPresenter
import com.vvp.wotstat.providers.DataProvider
import com.vvp.wotstat.views.SearchView
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchFragment : MvpAppCompatFragment(), AdapterListPlayers.OnItemClickListener, SearchView {


    @InjectPresenter
    lateinit var presenter: SearchPresenter

    //список игроков
    lateinit var adapter: AdapterListPlayers
    lateinit var manager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activity!!.setTitle(R.string.title_search_frag)


        // меню тулбара только на этом фрагменте
        setHasOptionsMenu(true)

        //setup
        manager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerListPlayers.layoutManager = manager
        adapter = AdapterListPlayers(this)
        recyclerListPlayers.adapter = adapter


        // поиск
        buttonSearch.setOnClickListener { CoroutineScope(Dispatchers.Main).launch { dataToPresenter() } }

        // сортировка
        floatingButtonSort.setOnClickListener { adapter.sortPlayersList() }
    }


    // fun for listener - go to DetailsFragment
    override fun onItemClick(view: View, selectedPlayer: Player) {

        // передача id выбранного игрока к DetailsFragment
        val bundle = bundleOf("selectedPlayer" to selectedPlayer)
        findNavController().navigate(R.id.action_to_detailsFragment, bundle)
    }



    @SuppressLint("RestrictedApi")
    override fun showProgress(show: Boolean) {
        if (show){
            recyclerListPlayers.visibility = View.GONE
            editTextNickname.isEnabled = false
            buttonSearch.isEnabled = false
            progressLoading.visibility = View.VISIBLE
            floatingButtonSort.visibility = View.GONE
        }
        else{
            recyclerListPlayers.visibility = View.VISIBLE
            editTextNickname.isEnabled = true
            buttonSearch.isEnabled = true
            progressLoading.visibility = View.GONE
        }
    }


    @SuppressLint("RestrictedApi")
    override fun updateData(players: ArrayList<Player>) {
        adapter.setupAdapter(arrayNewList = players)

        if (!players.isNullOrEmpty() && players.count() > 1){
            floatingButtonSort.visibility = View.VISIBLE
        }
        else{
            floatingButtonSort.visibility = View.GONE
        }

    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: Int) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


    private suspend fun dataToPresenter(){

        val newNickname = editTextNickname.text.toString()

        if (newNickname.isEmpty()){
            showMessage(R.string.empty_enter)
        }
        else{
            presenter.loadListPlayers(newNickname)
            presenter.writeRequestToDB(requestText = newNickname)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.history_button -> {  findNavController().navigate(R.id.action_to_requestHistoryFragment, null)  }
        }
        return super.onOptionsItemSelected(item)
    }




}
