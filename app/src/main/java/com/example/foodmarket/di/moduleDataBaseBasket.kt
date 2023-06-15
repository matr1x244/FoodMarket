package com.example.foodmarket.di

import com.example.foodmarket.date.local.DataBaseFoodsBuilder
import com.example.foodmarket.date.local.RepositoryBasket
import com.example.foodmarket.date.local.RepositoryBasketImpl
import com.example.foodmarket.ui.basket.BasketViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moduleDataBaseBasket = module {

    single(named("database")) {
        DataBaseFoodsBuilder.getInstance(androidContext())
    }
    single<RepositoryBasket>(named("basket")) {
        RepositoryBasketImpl(get(named("database")))
    }

    viewModel(named("basket_view_model")) { BasketViewModels(get(named("basket"))) }
}
