package com.example.foodmarket.date.web


import com.example.foodmarket.domain.data.ListFoods
import com.example.foodmarket.domain.data.RepositoryFoods

class RetrofitRequestImpl(private val api: FoodsAPI) : RepositoryFoods {

    override suspend fun listFoods(): ListFoods {
        return api.listFoods()
    }
}