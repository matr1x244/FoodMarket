package com.example.foodmarket.date.web


import com.example.foodmarket.domain.data.categoryFoods.ListCategoryFoods
import retrofit2.http.GET

interface FoodsAPI {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun listCategoryFoods(): ListCategoryFoods
}