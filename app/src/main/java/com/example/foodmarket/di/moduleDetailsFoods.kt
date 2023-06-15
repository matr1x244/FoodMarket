package com.example.foodmarket.di

import com.example.foodmarket.ui.main.detailWindow.DetailsFoodsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moduleDetailsFoods = module {

    viewModel(named("foods_detail_view_model")) {
        DetailsFoodsViewModel()
    }
}