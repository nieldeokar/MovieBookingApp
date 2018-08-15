package com.tentwenty.movieticket.di.module

import android.content.Context
import com.tentwenty.movieticket.feature.main.MainAdapter
import com.tentwenty.movieticket.feature.showtimes.ShowTimesAdapter
import com.tentwenty.movieticket.network.ApiService
import com.tentwenty.movieticket.network.RetrofitHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = context

    @Singleton
    @Provides
    fun provideApiService(): ApiService = RetrofitHelper().getApiService()

    @Provides
    fun provideMainAdapter(): MainAdapter = MainAdapter()

    @Provides
    fun provideShowTimesAdapter(): ShowTimesAdapter = ShowTimesAdapter()

}