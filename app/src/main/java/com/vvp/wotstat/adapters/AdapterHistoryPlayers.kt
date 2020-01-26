package com.vvp.wotstat.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vvp.wotstat.R
import com.vvp.wotstat.db.EntityDB


class AdapterHistoryPlayers (private var clickListener: OnItemClickListener) : RecyclerView.Adapter<AdapterHistoryPlayers.ViewHolder>() {


    //listener
    interface OnItemClickListener {
        fun onItemClick(view: View, selectedItem: EntityDB)
    }


    // внутренний массив для работы
    private var arrayListPlayers: ArrayList<EntityDB> = ArrayList()


    fun setupAdapter(historyList: List<EntityDB>){

        this.arrayListPlayers.clear()

        arrayListPlayers.addAll(historyList)

        //обновляем изменения
        notifyDataSetChanged()
    }


    fun updateDataFromDelete(){

        this.arrayListPlayers.clear()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_history_player, viewGroup, false))
    }


    override fun getItemCount(): Int {

        return this.arrayListPlayers.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindElements(entityDB = this.arrayListPlayers[position], action = clickListener)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        //find UI elements
        private var textHistoryNickname: TextView = itemView.findViewById(R.id.textHistoryNickname)


        // связка модели и UI
        @SuppressLint("SetTextI18n")
        fun bindElements(entityDB: EntityDB, action: OnItemClickListener){

            this.textHistoryNickname.text = entityDB.searchText

            // listener for item
            this.textHistoryNickname.setOnClickListener{  action.onItemClick(view = itemView, selectedItem = entityDB)  }
        }
    }


}