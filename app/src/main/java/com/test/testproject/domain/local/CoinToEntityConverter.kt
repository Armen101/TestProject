package com.test.testproject.domain.local

import com.test.testproject.domain.model.Coin

class CoinToEntityConverter {

    fun modelToEntity(model: Coin): CoinEntity {
        return CoinEntity(model.icon, model.name, model.price)
    }

    fun entityToModel(entity: CoinEntity): Coin {
        return Coin(entity.icon, entity.name, entity.price)
    }
}