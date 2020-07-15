package com.example.stocktime

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RawStock::class), version = 1)
public abstract class StockDB: RoomDatabase() {

    abstract fun stockDAO(): StockDAO

    companion object {
        @Volatile
        private var INSTANCE: StockDB? = null

        fun getDatabase(context: Context): StockDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        StockDB::class.java,
                        "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}