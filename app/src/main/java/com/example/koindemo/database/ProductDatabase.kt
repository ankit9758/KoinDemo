package com.example.koindemo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.koindemo.models.Products

@Database(entities = [Products::class], version = 1)
abstract class ProductDatabase :RoomDatabase(){
    abstract fun getProductDao():ProductDao
}