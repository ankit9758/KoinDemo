package com.example.koindemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koindemo.models.Products
import com.example.koindemo.repository.MainRepository
import com.example.koindemo.states.ProductState
import com.example.koindemo.utils.ResultHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel (private  val repository: MainRepository) :ViewModel(){
    /* val productsLiveData: LiveData<List<Products>>
    get() = repository.productList*/

    private val _productState=MutableStateFlow<ProductState>(ProductState.Idle)
    val productState=_productState.asStateFlow()

    init {
        getProductList()
    }

     fun getProductList() {
        _productState.value=ProductState.ShowLoading
        viewModelScope.launch {
            when(val res = repository.getProducts()){
                is ResultHandler.Error -> {
                    _productState.value=ProductState.Error(res.throwable)
                }
                is ResultHandler.Success<*> -> {
                    val response= (res.data as Response<*>)
                    if(response.isSuccessful){
                        val data= res.data.body()  as List<Products>
                        saveProductData(data as ArrayList<Products> /* = java.util.ArrayList<com.example.koindemo.models.Products> */)
                        _productState.value =ProductState.Success(data)
                    }else{
                        _productState.value= ProductState.Error(Throwable(("Something went wrong.")))
                    }

                }
            }
        }

    }
    private fun saveProductData(products: ArrayList<Products>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveProductsToDb(products)
        }
    }
}