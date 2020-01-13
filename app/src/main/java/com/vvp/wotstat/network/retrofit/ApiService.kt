package com.vvp.wotstat.network.retrofit

import com.vvp.wotstat.network.pojo.idUser.Model
import com.vvp.wotstat.uitls.TextConst
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    //search/account_id  убирается из строки адреса и переносится в Query

    // получение ID игрока (запрос с параметрами)
    @GET("account/list/?application_id=${TextConst.appID}")
    suspend fun getIdUser(@Query("search") name: String): Response<Model>

    // получение данных игрока по ID
    @GET("account/info/?application_id=${TextConst.appID}")
    suspend fun getDataUser(@Query("account_id") account_id: Int): Response<com.vvp.wotstat.network.pojo.dataUser.Model>

}