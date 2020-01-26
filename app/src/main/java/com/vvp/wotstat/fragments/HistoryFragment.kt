package com.vvp.wotstat.fragments


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vvp.wotstat.R
import com.vvp.wotstat.adapters.AdapterHistoryPlayers
import com.vvp.wotstat.db.EntityDB
import com.vvp.wotstat.presenters.HistoryPresenter
import com.vvp.wotstat.views.HistoryView
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HistoryFragment : MvpAppCompatFragment(), HistoryView, AdapterHistoryPlayers.OnItemClickListener {


    @InjectPresenter
    lateinit var presenter: HistoryPresenter


    private lateinit var adapter: AdapterHistoryPlayers
    private lateinit var manager: LinearLayoutManager

    lateinit var deleteButton: MenuItem


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity!!.setTitle(R.string.title_request_history_frag)

        setHasOptionsMenu(true)

        CoroutineScope(Dispatchers.Main).launch { presenter.sendDataToView() }
    }


    override fun setupRecyclerView() {

        manager = LinearLayoutManager(activity!!.applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerViewHistory.layoutManager = manager
        adapter = AdapterHistoryPlayers(this)
        recyclerViewHistory.adapter = adapter
    }



    override fun showHistoryList(arrayList: ArrayList<EntityDB>) {

        if(arrayList.isEmpty()){
            deleteButton.isVisible = false
        }
        else{
            adapter.setupAdapter(arrayList)
        }
    }



    override fun onItemClick(view: View, selectedItem: EntityDB) {

        val bundle = bundleOf("selectedItem" to selectedItem.searchText)

        //озврат на экран поиска
        findNavController().navigate(R.id.action_to_searchFragment, bundle)

    }



    override fun showAlertDialog() {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle(R.string.question_delete_all_from_db)

        builder.setPositiveButton(R.string.answer_yes){ _, _ ->
            presenter.deleteAll()
            adapter.updateDataFromDelete()
        }

        builder.setNegativeButton(R.string.answer_no){ dialog, _ ->
            dialog.cancel()
        }

        builder.setCancelable(true)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.history_frag_toolbar_menu, menu)
        deleteButton = menu.findItem(R.id.delete_all_from_db)

        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.delete_all_from_db -> {
                showAlertDialog()               // алерт на удаление
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
