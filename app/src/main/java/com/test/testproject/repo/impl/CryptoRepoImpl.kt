package com.test.testproject.repo.impl

import com.test.testproject.domain.local.CoinDao
import com.test.testproject.domain.local.CoinToEntityConverter
import com.test.testproject.domain.model.Coin
import com.test.testproject.domain.remote.ApiService
import com.test.testproject.repo.CryptoRepo

class CryptoRepoImpl(
    private val apiService: ApiService,
    private val dao: CoinDao,
    private val converter: CoinToEntityConverter
) : CryptoRepo {

    override suspend fun getCoins(skip: Int, limit: Int) = apiService.getCoins(skip, limit)

    override suspend fun insertFavoriteCoin(coin: Coin) {
        dao.insert(converter.modelToEntity(coin))
    }

    override suspend fun deleteFavoriteCoin(coin: Coin) {
        dao.delete(converter.modelToEntity(coin))
    }

//    override suspend fun getFavoriteCoins(): List<Coin> {
//        dao.getCoins()
//    }

}