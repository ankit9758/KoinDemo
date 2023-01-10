package com.example.koindemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.koindemo.database.ProductDao
import com.example.koindemo.models.Products
import com.example.koindemo.network.ApiService
import com.example.koindemo.utils.ResultHandler
import com.example.koindemo.utils.runIO

class MainRepository (private val apiService: ApiService,private val productDao: ProductDao) {
    private val _productList=MutableLiveData<List<Products>>()
    val productList:LiveData<List<Products>>
    get() = _productList

    suspend fun getProducts(): ResultHandler<Any> {
       return runIO { apiService.getProducts() }
    }

    suspend fun saveProductsToDb(products: ArrayList<Products>) {
        productDao.addProduct(products)
    }
}


