package com.example.stocktime

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stocks.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class RawStock(
        @PrimaryKey(autoGenerate = true) var ID: Int,
        @ColumnInfo(name = "isSelected") var isSelected: Boolean,
        @ColumnInfo(name = "symbol") var symbol: String)