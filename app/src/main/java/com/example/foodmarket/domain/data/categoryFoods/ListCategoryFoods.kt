package com.example.foodmarket.domain.data.categoryFoods

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListCategoryFoods(
    val сategories: List<FoodsCategory>
): Parcelable