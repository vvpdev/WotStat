package com.vvp.wotstat.providers

import android.util.Log
import com.google.gson.Gson
import com.vvp.wotstat.App
import com.vvp.wotstat.network.model.Player
import com.vvp.wotstat.network.pojo.StatPlayer
import com.vvp.wotstat.network.pojo.playerDetails.PlayerDetails
import com.vvp.wotstat.network.retrofit.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.json.JSONObject
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject


class DataProvider {

    init {
        App.diComponent!!.injectDataProvider(this)
    }


    @Inject
    lateinit var retrofitFactory: RetrofitFactory


    // получение Id игрока по никнейму
    suspend fun getIdUser(name: String):  Deferred<ArrayList<Player>> {

        return CoroutineScope(Dispatchers.IO).async {

            val arrayListPlayers: ArrayList<Player> = ArrayList()

            try {

                val response = retrofitFactory.getApiService().getIdUser(name)

                if (response.isSuccessful) {

                    val dataFromResponse = response.body()

                    when (dataFromResponse!!.status) {

                        "ok" -> {

                            // если ответ содержит игроков - записываем в массив
                            if (dataFromResponse.data!!.isNotEmpty()) {
                                dataFromResponse.data!!.forEach {  arrayListPlayers.add(Player(it.accountId!!, it.nickname!!)) }
                            }
                        }

                        "error" -> {

                            when (dataFromResponse.error!!.message) {

                                "INVALID_ACCOUNT_ID" -> {
                                    Log.i("proba", "неверный ID игрока")
                                }

                                "INVALID_SEARCH" -> {
                                    Log.i("proba", "введены неверные символы")
                                }

                                "SEARCH_NOT_SPECIFIED" -> {
                                    Log.i("proba", "не введен никнейм игрока")
                                }

                                else -> {
                                    Log.i("proba", "неизвестная ошибка")
                                }
                            }
                        }

                        else -> {
                            Log.i("proba", "неизвестная ошибка статуса")
                        }
                    }
                }
            }

            catch (e: Exception) {
                Log.i("proba", "ошибка выполнения запроса / нет инета")
            }
            return@async arrayListPlayers
        }
    }



    // получение статистики игрока по ID
    suspend fun getStatUser(account_id: Int): Deferred<Double?>{

        return CoroutineScope(Dispatchers.IO).async {

            var statUser = 0.0

                    val responseData = retrofitFactory.getApiServiceScalars().getStatUser(account_id = account_id)

                    if (responseData.isSuccessful) {

                        val bodyStatStr = responseData.body().toString()

                        val jsonResponse = JSONObject(bodyStatStr.substring(bodyStatStr.indexOf("{"), bodyStatStr.lastIndexOf("}") + 1))
                        val statDataPlayerStr = jsonResponse.getJSONObject("data")
                                                             .getJSONObject("$account_id")
                                                             .getJSONObject("statistics")
                                                             .getJSONObject("all")
                                                             .toString()

                        //___________________________________________________________________________
                        // сериализация в POJO

                        val gsonStat = Gson()
                        val statDataPlayerPojo = gsonStat.fromJson(statDataPlayerStr, StatPlayer::class.java)


                        if (statDataPlayerPojo.wins != null && statDataPlayerPojo.battles != null){

                                if (statDataPlayerPojo.wins != 0 && statDataPlayerPojo.battles != 0){

                                    val winsDouble = statDataPlayerPojo.wins!!.toDouble()
                                    val battlesDouble = statDataPlayerPojo.battles!!.toDouble()

                                    // приводим к виду - 2 знака после запятой
                                    statUser = BigDecimal((winsDouble/battlesDouble) * 100).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                                }
                        }
                        else{
                            statUser = 0.0
                        }
                    }

            return@async statUser
        }
    }



    // получение подробных данных игрока по ID
   suspend fun getPlayerData(account_id: Int): Deferred<PlayerDetails>{

       return CoroutineScope(Dispatchers.IO).async {

           var playerDetailsObj = PlayerDetails()

           val responsePlayerDetails = retrofitFactory.getApiServiceScalars().getDataPlayer(account_id)

           if (responsePlayerDetails.isSuccessful){

               val responsePlayerDetailsSrt = responsePlayerDetails.body().toString()

               Log.i("proba", responsePlayerDetailsSrt)

               val jsonResponse = JSONObject(responsePlayerDetailsSrt.substring(responsePlayerDetailsSrt.indexOf("{"), responsePlayerDetailsSrt.lastIndexOf("}") + 1))

               val playerDataStr = jsonResponse.getJSONObject("data")
                                                       .getJSONObject("$account_id")
                                                       .toString()

               //___________________________________________________________________________
               // сериализация в POJO

               val gsonStat = Gson()
               playerDetailsObj = gsonStat.fromJson(playerDataStr, PlayerDetails::class.java)
           }

           return@async playerDetailsObj
       }
   }




}