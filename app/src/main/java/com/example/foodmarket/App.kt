package com.example.foodmarket

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.foodmarket.di.moduleCategoryFoods
import com.example.foodmarket.di.moduleDataBaseBasket
import com.example.foodmarket.di.moduleDetailsFoods
import com.example.foodmarket.di.moduleListFoods
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(moduleCategoryFoods, moduleListFoods, moduleDetailsFoods, moduleDataBaseBasket)
        }
    }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app