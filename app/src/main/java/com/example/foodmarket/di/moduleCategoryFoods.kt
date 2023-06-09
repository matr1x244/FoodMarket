package com.example.foodmarket.di

import com.example.foodmarket.date.web.FoodsAPI
import com.example.foodmarket.date.web.RetrofitRequestImpl
import com.example.foodmarket.domain.data.RepositoryCategoryFoods
import com.example.foodmarket.ui.main.mainWindow.FoodsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val moduleCategoryFoods = module {

    val apiUrl = "https://run.mocky.io/v3/"

    single<RepositoryCategoryFoods>(named("API_category_foods")) { RetrofitRequestImpl(get()) }
    single<FoodsAPI> { get<Retrofit>().create(FoodsAPI::class.java) }

    single {
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(get(named("rx_java_adapter")))
            .addConverterFactory(get(named("gson_converter")))
            .build()
    }

    factory<Converter.Factory>(named("gson_converter")) { GsonConverterFactory.create() }
    factory<CallAdapter.Factory>(named("rx_java_adapter")) { RxJava3CallAdapterFactory.create() }

    viewModel(named("foods_category_view_model")) { FoodsViewModel(get(named("API_category_foods"))) }
}