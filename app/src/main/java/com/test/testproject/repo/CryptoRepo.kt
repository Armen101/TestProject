package com.test.testproject.repo

import com.test.testproject.domain.model.Coin
import com.test.testproject.domain.model.CoinsResponse

interface CryptoRepo {

    suspend fun getCoins(skip: Int, limit: Int): CoinsResponse

    suspend fun insertFavoriteCoin(coin: Coin)

    suspend fun deleteFavoriteCoin(coin: Coin)

//    suspend fun getFavoriteCoins() : List<Coin>

}