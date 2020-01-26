package com.vvp.wotstat.network.retrofit

import com.vvp.wotstat.uitls.TextConst
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitFactory {


    //companion object {

            private fun getRetrofitInstance(): Retrofit {

                return  Retrofit.Builder()
                    .baseUrl(TextConst.baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            fun getApiService(): ApiService = getRetrofitInstance().create(ApiService::class.java)


            //ScalarsConverterFactory  - для получения String response
            private fun getRetrofitInstanceScalars(): Retrofit {

                return  Retrofit.Builder()
                    .baseUrl(TextConst.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
            }
            fun getApiServiceScalars(): ApiService = getRetrofitInstanceScalars().create(ApiService::class.java)
   // }
}