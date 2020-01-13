package com.vvp.wotstat.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vvp.wotstat.Model.Player
import com.vvp.wotstat.R
import com.vvp.wotstat.network.pojo.idUser.Datum

class AdapterListPlayers: RecyclerView.Adapter<AdapterListPlayers.ViewHolder>() {


    private var arrayListPlayers: ArrayList<Player> = ArrayList()

    fun setupAdapter(datumArrayList: ArrayList<Datum>){

        this.arrayListPlayers.clear()

        datumArrayList.forEach { arrayListPlayers.add(Player(it.nickname!!)) }

        //обновляем изменения
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_player, viewGroup, false))
    }


    override fun getItemCount(): Int {

        return this.arrayListPlayers.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindElements(player = this.arrayListPlayers[position])
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        //find UI elements
        private var textNickname: TextView = itemView.findViewById(R.id.textNickname)

        // связка модели и UI
        fun bindElements(player: Player){

            this.textNickname.text = player.nickname
        }
    }



}