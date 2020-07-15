package com.example.stocktime

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.stocks.Constants

@Dao
interface StockDAO{

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    fun getAll(): List<RawStock>

    @Insert
    fun insertAll(vararg rawStock: RawStock)


    @Delete
    fun delete(rawStock: RawStock)

}