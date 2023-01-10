package com.example.koindemo.network

import com.example.koindemo.models.Products
import com.example.koindemo.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(AppConstants.PRODUCT_LIST)
    suspend fun getProducts(): Response<List<Products>>
}
