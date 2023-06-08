package com.example.foodmarket.domain.data

import com.example.foodmarket.domain.data.listFoods.ListFoods

interface RepositoryListFoods {

    suspend fun listFoods(): ListFoods
}