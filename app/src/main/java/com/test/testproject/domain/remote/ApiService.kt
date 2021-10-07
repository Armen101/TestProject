package com.test.testproject.domain.remote

import com.test.testproject.domain.model.CoinsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("public/v1/coins")
    suspend fun getCoins(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): CoinsResponse

}