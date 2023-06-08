package com.example.foodmarket.domain.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodsCategory(
    val id: Int,
    val image_url: String,
    val name: String
): Parcelable