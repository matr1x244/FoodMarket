package com.example.foodmarket.date.web


import com.example.foodmarket.domain.data.categoryFoods.ListCategoryFoods
import com.example.foodmarket.domain.data.listFoods.ListFoods
import retrofit2.http.GET

interface FoodsAPI {
    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun listCategoryFoods(): ListCategoryFoods

    @GET("aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    suspend fun listFoods(): ListFoods
}