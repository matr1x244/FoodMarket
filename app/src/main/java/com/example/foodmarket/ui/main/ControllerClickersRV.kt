package com.example.foodmarket.ui.main

import com.example.foodmarket.domain.data.categoryFoods.FoodsCategory
import com.example.foodmarket.domain.data.listFoods.ListFoods

interface ControllerClickersRV {

    fun openDetailsFragment(item: ListFoods.Dishe)

    fun openListFragment(item: FoodsCategory)
}