package com.vvp.wotstat.di.moduls

import com.vvp.wotstat.providers.DataProvider
import dagger.Module
import dagger.Provides


@Module
class DataProviderModule {

    @Provides
    fun provideDataProvider(): DataProvider{
        return DataProvider() }
}