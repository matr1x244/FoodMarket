package com.example.foodmarket.domain.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListFoods(
    val сategories: List<FoodsCategory>
): Parcelable