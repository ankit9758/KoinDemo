package com.example.koindemo

import android.app.Application
import com.example.koindemo.di.modules.appModule
import com.example.koindemo.di.modules.interfaceModule
import com.example.koindemo.di.modules.networkModule
import com.example.koindemo.di.modules.productDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            modules(networkModule,appModule,interfaceModule,productDatabase)
            androidContext(this@KoinApplication)
        }
    }
}