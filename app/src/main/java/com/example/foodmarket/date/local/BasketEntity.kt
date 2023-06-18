package com.example.foodmarket.date.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket_table")
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val image_url: String,
    var name: String?,
    val price: String?,
    val weight: String?,
    val sum: Int?
)
