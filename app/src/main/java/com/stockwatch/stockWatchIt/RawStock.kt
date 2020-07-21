package com.stockwatch.stockWatchIt

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class RawStock(
        @ColumnInfo(name = "isSelected") var isSelected: Boolean,
        @ColumnInfo(name = "symbol") var symbol: String){

    @PrimaryKey(autoGenerate = true) var ID = Int
}