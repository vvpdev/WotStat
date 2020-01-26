package com.vvp.wotstat.network.retrofit

import com.vvp.wotstat.network.pojo.Model
import com.vvp.wotstat.uitls.TextConst
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // получение ID игрока
    @GET("account/list/?application_id=${TextConst.appID}")
    suspend fun getIdUser(@Query("search") name: String): Response<Model>

    // получение статистики игрока
    @GET("account/info/?application_id=${TextConst.appID}&fields=statistics.all.battles%2C+statistics.all.wins")
    suspend fun getStatUser(@Query("account_id") account_id: Int): Response<String>


    // получение данных игрока по ID
    @GET("account/info/?application_id=${TextConst.appID}")
    suspend fun getDataPlayer(@Query("account_id") account_id: Int): Response<String>

}