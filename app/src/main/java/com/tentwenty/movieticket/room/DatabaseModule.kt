package com.tentwenty.movieticket.room

import android.arch.persistence.room.Room
import android.content.Context
import com.tentwenty.movieticket.utils.constants.DBConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Named(DATABASE)
    internal fun provideDatabaseName(): String {
        return DBConstants.DB_NAME
    }

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context, @Named(DATABASE) databaseName: String): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.moviesDao()
    }

    companion object {
        private const val DATABASE = "database_name"
    }
}