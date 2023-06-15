package com.example.foodmarket.date.local

import com.example.foodmarket.domain.data.listFoods.ListFoods

class RepositoryBasketImpl(private val database: DataBaseFoods) : RepositoryBasket {

    override suspend fun saveBasket(
        name: String,
        image_url: String,
        price: String,
        weight: String
    ) {
        database.foodsDao().insert(
            BasketEntity(
                name = name,
                image_url = image_url,
                price = price,
                weight = weight
            )
        )
    }

    override suspend fun deleteBasket() {
        return database.foodsDao().deleteAll()
    }

    override suspend fun getBasket(): List<BasketEntity> {
        return database.foodsDao().allBasket()
    }
}