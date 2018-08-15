package com.tentwenty.movieticket.room

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.tentwenty.movieticket.feature.shared.model.CinemaEntity
import com.tentwenty.movieticket.feature.shared.model.Movie
import com.tentwenty.movieticket.feature.shared.model.ShowTimeEntity
import com.tentwenty.movieticket.utils.constants.DBConstants
import java.util.concurrent.Executors


@Database(entities = [Movie::class, CinemaEntity::class, ShowTimeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MovieDao

    abstract fun cinemasDao(): CinemaDao

    abstract fun showTimesDao(): ShowTimeDao


    companion object {
        private var INSTANCE: AppDatabase? = null


        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE as AppDatabase
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    DBConstants.DB_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadScheduledExecutor().execute(Runnable {
                                getInstance(context).cinemasDao().insert(CinemaEntity.populateData(context))
                            })
                        }
                    })
                    .build()
        }
    }
}