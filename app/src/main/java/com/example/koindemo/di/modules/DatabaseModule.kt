package com.example.koindemo.di.modules

import android.app.Application
import androidx.room.Room
import com.example.koindemo.database.ProductDao
import com.example.koindemo.database.ProductDatabase
import com.example.koindemo.utils.AppConstants
import org.koin.dsl.module

fun provideUserDataBase(app: Application): ProductDatabase {
    return Room.databaseBuilder(app, ProductDatabase::class.java, AppConstants.DB_NAME)
        .fallbackToDestructiveMigration().build()
}

fun provideDao(db: ProductDatabase): ProductDao = db.getProductDao()



val productDatabase = module {
    single { provideUserDataBase(get()) }
    single { provideDao(get()) }
}