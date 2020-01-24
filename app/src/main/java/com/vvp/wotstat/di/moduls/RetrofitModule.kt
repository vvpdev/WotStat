package com.vvp.wotstat.di.moduls

import com.vvp.wotstat.network.retrofit.RetrofitFactory
import dagger.Module
import dagger.Provides


@Module
class RetrofitModule {

    @Provides
    fun provideRetrofitFactory(): RetrofitFactory {
        return RetrofitFactory()
    }

}