package com.example.foodmarket.di

import com.example.foodmarket.date.web.FoodsAPI
import com.example.foodmarket.date.web.RetrofitRequestImpl
import com.example.foodmarket.domain.data.RepositoryListFoods
import com.example.foodmarket.ui.main.categoryWindow.FoodsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val moduleListFoods = module {

    single<RepositoryListFoods>(named("API_list_foods")) { RetrofitRequestImpl(get()) }
    single<FoodsAPI> { get<Retrofit>().create(FoodsAPI::class.java) }

    viewModel(named("foods_list_view_model")) { FoodsListViewModel(get(named("API_list_foods"))) }
}