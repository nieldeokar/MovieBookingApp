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


    /*
       companion object {
        const val DATABASE = "database_name"
    }


     @Provides
     @Named(DATABASE)
     fun provideDatabaseName(): String {
         return DBConstants.DB_NAME
     }

     @Provides
     @Singleton
     internal fun provideAppDatabase(context: Context, @Named(DATABASE) databaseName: String): AppDatabase {
         return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                 .addCallback(object : RoomDatabase.Callback() {
                     override fun onCreate(db: SupportSQLiteDatabase) {
                         super.onCreate(db)
                         Executors.newSingleThreadScheduledExecutor().execute(
                                 Runnable { databaseName(context).dataDao().insertAll(DataEntity.populateData()) })
                     }
                 })

                 .build()
     }

      @Provides
     @Singleton
     internal fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
         return appDatabase.moviesDao()
     }
 */


}