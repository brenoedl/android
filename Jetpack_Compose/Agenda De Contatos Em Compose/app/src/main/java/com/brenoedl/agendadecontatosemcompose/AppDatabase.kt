package com.brenoedl.agendadecontatosemcompose

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brenoedl.agendadecontatosemcompose.constantes.Constantes
import com.brenoedl.agendadecontatosemcompose.dao.ContatosDao
import com.brenoedl.agendadecontatosemcompose.model.Contatos

@Database(entities = [Contatos::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun contatosDao(): ContatosDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constantes.BD_CONTATOS
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}