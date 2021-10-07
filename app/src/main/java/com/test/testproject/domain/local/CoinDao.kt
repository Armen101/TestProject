package com.test.testproject.domain.local

import androidx.room.*

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin ")
    suspend fun getCoins(): List<CoinEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: CoinEntity): Long

    @Delete
    suspend fun delete(entity: CoinEntity)

}