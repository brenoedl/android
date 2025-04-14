package com.brenoedl.agendadecontatos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brenoedl.agendadecontatos.model.Contato
import com.brenoedl.agendadecontatos.dao.ContatoDao

@Database(entities = [Contato::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun contatoDao(): ContatoDao

    companion object {
        private const val DATABASE_NAME = "DB_CONTATOS"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getIntance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}