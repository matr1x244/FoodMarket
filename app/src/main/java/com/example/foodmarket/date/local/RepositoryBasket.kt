package com.example.foodmarket.date.local

interface RepositoryBasket {

    suspend fun saveBasket(name: String, image_url: String, price: String, weight: String)

    suspend fun deleteBasketAll()

    suspend fun updateBasket(sum: Int?)

    suspend fun getAllBasket(): List<BasketEntity>
}