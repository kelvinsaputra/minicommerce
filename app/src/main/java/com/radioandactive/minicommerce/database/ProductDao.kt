package com.radioandactive.minicommerce.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductFromDatabase")
    fun getAll(): List<ProductFromDatabase>

    @Query("SELECT * FROM ProductFromDatabase WHERE uid IN (:productIds)")
    fun loadAllByIds(productIds: IntArray): List<ProductFromDatabase>

    @Query("SELECT * FROM ProductFromDatabase WHERE title LIKE :pTitle AND " +
            "price LIKE :pPrice LIMIT 1")
    fun findByName(pTitle: String, pPrice: String): ProductFromDatabase

    @Query("SELECT * FROM ProductFromDatabase WHERE title LIKE :term")
    fun searchFor(term: String): List<ProductFromDatabase>

    @Insert
    fun insertAll(vararg products: ProductFromDatabase)

    @Delete
    fun delete(products: ProductFromDatabase)
}