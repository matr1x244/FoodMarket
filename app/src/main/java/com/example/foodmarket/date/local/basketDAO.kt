package com.example.foodmarket.date.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface basketDAO {

    @Query("SELECT * FROM basket_table")
    fun allBasket(): List<BasketEntity>

    @Query("SELECT * FROM basket_table WHERE name LIKE :name")
    fun getDataByFoods(name: String): List<BasketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: BasketEntity)

    @Update
    fun update(entity: BasketEntity)

    @Delete
    fun delete(entity: BasketEntity)

    @Query("DELETE FROM basket_table WHERE name = :name")
    fun deleteByBasket(name: String)

    @Query("DELETE FROM basket_table")
    fun deleteAll()
}