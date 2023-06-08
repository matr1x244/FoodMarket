package com.example.foodmarket.date.web


import com.example.foodmarket.domain.data.RepositoryCategoryFoods
import com.example.foodmarket.domain.data.RepositoryListFoods
import com.example.foodmarket.domain.data.categoryFoods.ListCategoryFoods
import com.example.foodmarket.domain.data.listFoods.ListFoods

class RetrofitRequestImpl(private val api: FoodsAPI) : RepositoryCategoryFoods,
    RepositoryListFoods {
    override suspend fun listCategoryFoods(): ListCategoryFoods {
        return api.listCategoryFoods()
    }

    override suspend fun listFoods(): ListFoods {
        return api.listFoods()
    }
}