package com.example.koindemo.di.modules

import com.example.koindemo.network.ApiService
import com.example.koindemo.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideRetrofit(gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(AppConstants.DEFAULT_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}


fun provideOkHttpClient(): OkHttpClient {
    val okHttpBuilder = OkHttpClient.Builder()
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    okHttpBuilder.addInterceptor(loggingInterceptor)
    okHttpBuilder.readTimeout(2, TimeUnit.MINUTES)
    okHttpBuilder.connectTimeout(2, TimeUnit.MINUTES)
    okHttpBuilder.writeTimeout(2, TimeUnit.MINUTES)
    return okHttpBuilder.build()
}


fun provideGson(): Gson {
    return GsonBuilder()
        .setLenient()
        .create()
}


fun provideNewRetrofit(gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(AppConstants.DEFAULT_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}
val networkModule = module {
    single{ provideGson()}
    single(named("retrofit")) { provideRetrofit(get()) }
    single(named("retrofitNew")) { provideNewRetrofit(get()) }
    single { provideApiService(get(named("retrofit"))) }
}