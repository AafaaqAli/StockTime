package com.example.stocktime

class StockRepository (private val stockDao: StockDAO){
    suspend fun insert(rawStock: RawStock){
        stockDao.insertAll(rawStock)
    }

    suspend fun delete(rawStock: RawStock){
        stockDao.delete(rawStock)
    }

    suspend fun getAll(): List<RawStock>{
        return stockDao.getAll()
    }

}