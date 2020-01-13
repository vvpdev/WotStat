package com.vvp.wotstat.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vvp.wotstat.Model.Player
import com.vvp.wotstat.R
import com.vvp.wotstat.adapters.AdapterListPlayers
import com.vvp.wotstat.network.pojo.idUser.Datum
import com.vvp.wotstat.network.retrofit.RetrofitFactory
import com.vvp.wotstat.providers.DataProvider
import kotlinx.coroutines.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    lateinit var provider: DataProvider

    //список игроков
    lateinit var recyclerListPlayers: RecyclerView
    lateinit var adapter: AdapterListPlayers
    lateinit var manager: LinearLayoutManager

    //кнопки и текстовые поля
    lateinit var editTextNickname: EditText
    lateinit var buttonSearch: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init UI
        recyclerListPlayers = findViewById(R.id.recyclerListPlayers)
        editTextNickname = findViewById(R.id.editTextNickname)
        buttonSearch = findViewById(R.id.buttonSearch)


        //setup
        provider = DataProvider()
        manager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerListPlayers.layoutManager = manager
        adapter = AdapterListPlayers()
        recyclerListPlayers.adapter = adapter


        buttonSearch.setOnClickListener { CoroutineScope(Dispatchers.Main).async { loadListPlayers() } }
    }


    // загрузить список игроков
    private suspend fun loadListPlayers()  {

        val nicknameForSearch: String = editTextNickname.text.toString()

        // корутина ввода/вывода
        CoroutineScope(Dispatchers.IO).launch {

            val list = provider.getIdUser(nicknameForSearch).await()

            // обновление UI - корутина добавляется в главный поток
            CoroutineScope(Dispatchers.Main).launch {

                if (list.isEmpty()){
                    Toast.makeText(this@MainActivity, "такого игрока нет", Toast.LENGTH_SHORT).show()
                    adapter.setupAdapter(list)
                }
                else{
                    adapter.setupAdapter(list)
                }
            }
        }
    }


}