package com.test.testproject.domain.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin")
data class CoinEntity(
    val icon: String?,
    val name: String?,
    val price: Double?
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}