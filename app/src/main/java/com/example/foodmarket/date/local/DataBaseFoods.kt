package com.example.foodmarket.date.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(
    entities = [
       BasketEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class DataBaseFoods: RoomDatabase() {

    abstract fun foodsDao(): basketDAO
}

object DataBaseFoodsBuilder {
    private var instance: DataBaseFoods? = null
    const val DB_NAME = "add_basket_sum.db"

    fun getInstance(context: Context): DataBaseFoods {
        if (instance == null) {
            synchronized(DataBaseFoods::class) {
                instance = buildRoomDB(context)
            }
        }
        return instance!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            DataBaseFoods::class.java,
            DB_NAME
        ).build()
}