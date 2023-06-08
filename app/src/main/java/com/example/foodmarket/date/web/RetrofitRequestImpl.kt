package com.example.foodmarket.date.web


import com.example.foodmarket.domain.data.categoryFoods.ListCategoryFoods
import com.example.foodmarket.domain.data.RepositoryFoods

class RetrofitRequestImpl(private val api: FoodsAPI) : RepositoryFoods {

    override suspend fun listCategoryFoods(): ListCategoryFoods {
        return api.listCategoryFoods()
    }
}