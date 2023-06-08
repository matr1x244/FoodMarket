package com.example.foodmarket.domain.data

import com.example.foodmarket.domain.data.categoryFoods.ListCategoryFoods

interface RepositoryCategoryFoods {

    suspend fun listCategoryFoods(): ListCategoryFoods
}