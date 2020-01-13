package com.vvp.wotstat.providers

import android.util.Log
import com.vvp.wotstat.network.pojo.idUser.Datum
import com.vvp.wotstat.network.retrofit.RetrofitFactory
import kotlinx.coroutines.*


class DataProvider {


    // получение Id игрока по никнейму

    // если вернуть из корутины - то тип Deferred обязателен
    suspend fun getIdUser(name: String):  Deferred<ArrayList<Datum>> {

        return CoroutineScope(Dispatchers.IO).async {

            val arrayListDatum: ArrayList<Datum> = ArrayList()

            try {

                val response = RetrofitFactory.getApiService()

                if (response.getIdUser(name = name).isSuccessful) {
                    val model = response.getIdUser(name = name).body()

                    when (model!!.status) {

                        "ok" -> {

                            // запрос удачный, но такого игрока нет - пустой массив с данными
                            if (model.data!!.isEmpty()) {
                                Log.i("proba", "такого игрока нет")
                            }

                            // данные игрока или массив игроков (в зависимости от результатов поиска)
                            else {
                                arrayListDatum.addAll(model.data!!)
                            }
                        }

                        "error" -> {

                            when (model.error!!.message) {

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
                } else {

                    Log.i("proba", "запрос выполнен неудачно")
                }
            } catch (e: Exception) {
                Log.i("proba", "ошибка выполнения запроса / нет инета")
            }

            return@async arrayListDatum
        }
    }





    // получение данных игрока по Id
    suspend fun getDataUser(account_id: Int){

        CoroutineScope(Dispatchers.IO).launch {

            val responseData = RetrofitFactory.getApiService()

            withContext(Dispatchers.Main){

                try {

                    if (responseData.getDataUser(account_id = account_id).isSuccessful){
                        val dataUser = responseData.getDataUser(account_id = account_id).body()

                        val userRating = dataUser!!.data!!.userData!!.globalRating
                        Log.i("proba", "рейтинг игрока = $userRating")

                    }
                    else{
                        Log.i("proba", "ошибка получения рейтинга")
                    }
                }
                catch (e: Exception){
                    Log.i("proba", "ошибка выполнения запроса / нет инета")
                }
            }
        }
    }

}