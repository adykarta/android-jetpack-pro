package com.dicoding.film.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dicoding.film.data.model.FilmEntity
import com.dicoding.film.utils.DataConverter

@Database(entities = [FilmEntity::class],
    version = 1,
    exportSchema = false)

@TypeConverters(DataConverter::class)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

    companion object {

        @Volatile
        private var INSTANCE: FilmDatabase? = null

        fun getInstance(context: Context): FilmDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FilmDatabase::class.java,
                    "Film.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}