package com.example.koindemo.di.modules

import com.example.koindemo.repository.MainRepository
import com.example.koindemo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { MainRepository(get(),get()) }
    viewModel { MainViewModel(get()) }
}


