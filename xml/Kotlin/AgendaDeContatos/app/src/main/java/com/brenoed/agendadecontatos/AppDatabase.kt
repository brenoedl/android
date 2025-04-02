package com.brenoed.agendadecontatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brenoed.agendadecontatos.dao.ContatosDao
import com.brenoed.agendadecontatos.model.Contatos

@Database(entities = [Contatos::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contatosDao(): ContatosDao

    companion object {
        private const val DATABASE_NAME: String = "DB_CONTATOS"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return INSTANCE?: synchronized(this){
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