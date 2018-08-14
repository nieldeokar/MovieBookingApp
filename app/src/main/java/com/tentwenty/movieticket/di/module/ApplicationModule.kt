package com.tentwenty.movieticket.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.tentwenty.movieticket.feature.main.MainAdapter
import com.tentwenty.movieticket.network.ApiService
import com.tentwenty.movieticket.network.RetrofitHelper
import com.tentwenty.movieticket.room.AppDatabase
import com.tentwenty.movieticket.room.MovieDao
import com.tentwenty.movieticket.utils.constants.DBConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApplicationModule(private val context: Context) {

    companion object {
        const val DATABASE = "database_name"
    }

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = context

    @Singleton
    @Provides
    fun provideApiService(): ApiService = RetrofitHelper().getApiService()

    @Provides
    @Named(DATABASE)
    fun provideDatabaseName(): String {
        return DBConstants.DB_NAME
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context, @Named(DATABASE) databaseName: String): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
    }

    @Provides
    fun provideMainAdapter(): MainAdapter = MainAdapter()

    @Provides
    @Singleton
    internal fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.moviesDao()
    }



}