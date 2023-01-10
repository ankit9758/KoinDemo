package com.example.koindemo.states

import com.example.koindemo.models.Products


sealed class ProductState{
    object ShowLoading:ProductState()
    object Idle:ProductState()
    class Success(val data: List<Products>) : ProductState()
    class Error(val throwable: Throwable) : ProductState()
}
