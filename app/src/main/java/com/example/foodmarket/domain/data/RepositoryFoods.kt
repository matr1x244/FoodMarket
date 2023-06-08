package com.example.foodmarket.domain.data

import com.example.foodmarket.domain.data.categoryFoods.ListCategoryFoods

interface RepositoryFoods {

    suspend fun listCategoryFoods(): ListCategoryFoods
}