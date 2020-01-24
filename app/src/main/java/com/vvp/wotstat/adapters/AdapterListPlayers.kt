package com.vvp.wotstat.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vvp.wotstat.model.Player
import com.vvp.wotstat.R

class AdapterListPlayers (private var clickListener: OnItemClickListener) : RecyclerView.Adapter<AdapterListPlayers.ViewHolder>() {


    //listener
    interface OnItemClickListener {
        fun onItemClick(view: View, selectedPlayer: Player)
    }


    // внутренний массив для работы
    private var arrayListPlayers: ArrayList<Player> = ArrayList()

    fun setupAdapter(arrayNewList: ArrayList<Player>){

       this.arrayListPlayers.clear()

       arrayListPlayers.addAll(arrayNewList)

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

        holder.bindElements(player = this.arrayListPlayers[position], action = clickListener)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        //find UI elements
        private var textNicknameCell: TextView = itemView.findViewById(R.id.textNicknameCell)
        private var textStatCell: TextView = itemView.findViewById(R.id.textStatCell)
        private var circleStatIcon: ImageView = itemView.findViewById(R.id.circleStatIcon)


        // связка модели и UI
        @SuppressLint("SetTextI18n")
        fun bindElements(player: Player, action: OnItemClickListener){

            this.textNicknameCell.text = player.nickname
            this.textStatCell.text = player.statistics.toString() + "%"

            //цвета статистики
            when (player.statistics){

                in 0.00 ..  42.00 -> this.circleStatIcon.setColorFilter(Color.parseColor("#C30A0A0A"))
                in 43.00 .. 46.00 -> this.circleStatIcon.setColorFilter(Color.parseColor("#ff0000"))
                in 46.00 .. 49.00 -> this.circleStatIcon.setColorFilter(Color.parseColor("#ffa600"))
                in 49.00 .. 52.00 -> this.circleStatIcon.setColorFilter(Color.parseColor("#CACA33"))
                in 52.00 .. 57.00 -> this.circleStatIcon.setColorFilter(Color.parseColor("#008100"))
                in 57.00 .. 63.00 -> this.circleStatIcon.setColorFilter(Color.parseColor("#12C5B5"))
                in 63.00 .. 70.00 -> this.circleStatIcon.setColorFilter(Color.parseColor("#ee81ee"))
            }

            // listener for item
            this.textNicknameCell.setOnClickListener{  action.onItemClick(view = itemView, selectedPlayer = player)  }
        }
    }

    // сортировка массива по убыванию статистики
    fun sortPlayersList(){

        arrayListPlayers.sortByDescending { player -> player.statistics }
        notifyDataSetChanged()
    }

}