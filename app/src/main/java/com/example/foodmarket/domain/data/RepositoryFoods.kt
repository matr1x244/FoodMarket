package com.example.foodmarket.domain.data

interface RepositoryFoods {

    suspend fun listFoods(): ListFoods
}