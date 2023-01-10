package com.example.koindemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.hiltdemo.utils.hide
import com.example.hiltdemo.utils.show
import com.example.hiltdemo.utils.toast
import com.example.koindemo.R
import com.example.koindemo.databinding.ActivityMainBinding
import com.example.koindemo.di.component.Component
import com.example.koindemo.models.Products
import com.example.koindemo.states.ProductState
import com.example.koindemo.ui.adapter.ProductListAdapter
import com.example.koindemo.utils.Main
import com.example.koindemo.viewmodel.MainViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
     private val component= Component()
    private lateinit var productListAdapter: ProductListAdapter

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.main.getDemo()
       // Main(get(), get()).getDemo()
        setUpViewModel()
        setupViews()
        setupObservers()

    }

    private fun setUpViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }

    //initialize the adapter and attach with recyclerview
    private fun setupViews() {
        productListAdapter = ProductListAdapter(this)
        binding.rvProduct.apply {
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL))
            adapter = productListAdapter
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getProductList()
            binding.swipeRefresh.isRefreshing = false
        }

    }

    private fun setupObservers() {
        lifecycleScope.launchWhenStarted {
            with(binding) {
                swipeRefresh.isRefreshing = false
                viewModel.productState.collect {
                    when (it) {
                        is ProductState.Error -> {
                            progressBar.hide()
                            val message = it.throwable.message ?: ""
                            tvNoData.text = message
                            tvNoData.show()
                            toast(message)
                        }
                        ProductState.ShowLoading -> {
                            progressBar.show()
                        }
                        is ProductState.Success -> {
                            progressBar.hide()
                            showNoDataFound(it.data)
                            productListAdapter.setProductList(it.data)
                        }
                        ProductState.Idle -> {

                        }
                    }
                }
            }

        }
    }

    private fun showNoDataFound(itemList: List<Products>) {
        with(binding) {
            if (itemList.isEmpty()) {
                tvNoData.text = "No Data Found."
                tvNoData.show()
                rvProduct.hide()
            } else {
                tvNoData.hide()
                rvProduct.show()
            }
        }

    }

}