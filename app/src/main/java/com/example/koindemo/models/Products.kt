package com.example.koindemo.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class Products (
  @PrimaryKey(autoGenerate = false)
  val id:Int,
  val title:String,
  val description:String,
  val category:String,
  val image:String,
  val price:Float,
)