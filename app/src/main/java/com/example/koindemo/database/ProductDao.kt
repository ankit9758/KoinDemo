package com.example.koindemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.koindemo.models.Products
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(user: ArrayList<Products>)

    @Query("SELECT * FROM Products")
    fun getAllProduct(): Flow<List<Products>>
}