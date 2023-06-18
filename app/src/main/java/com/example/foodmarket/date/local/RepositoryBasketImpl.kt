package com.example.foodmarket.date.local

class RepositoryBasketImpl(private val database: DataBaseFoods) : RepositoryBasket {

    override suspend fun saveBasket(
        name: String,
        image_url: String,
        price: String,
        weight: String,
    ) {
        database.foodsDao().insert(
            BasketEntity(
                name = name,
                image_url = image_url,
                price = price,
                weight = weight,
                sum = 1
            )
        )
    }

    override suspend fun updateBasket(sum: Int?) {
       database.foodsDao().updateSumByBasket(sum)
    }

    override suspend fun deleteBasketAll() {
        return database.foodsDao().deleteAll()
    }

    override suspend fun getAllBasket(): List<BasketEntity> {
        return database.foodsDao().allBasket()
    }
}