package com.vvp.wotstat.di.modules

import com.vvp.wotstat.network.retrofit.RetrofitFactory
import dagger.Module
import dagger.Provides


@Module
class RetrofitFactoryModule {


    @Provides
    fun provideRetrofitFactory(): RetrofitFactory{
        return RetrofitFactory()
    }
}