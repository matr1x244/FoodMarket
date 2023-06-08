package com.example.foodmarket.domain.data.listFoods

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListFoods(
    val dishes: List<Dishe>
): Parcelable