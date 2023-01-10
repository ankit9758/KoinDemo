package com.example.koindemo.di.modules

import com.example.koindemo.utils.DemoImpl
import com.example.koindemo.utils.DemoOne
import com.example.koindemo.utils.DemoTwo
import com.example.koindemo.utils.Main
import org.koin.dsl.binds
import org.koin.dsl.module

val interfaceModule = module {
    factory { DemoImpl() } binds arrayOf(DemoOne::class, DemoTwo::class)
//   factory<DemoOne> { DemoImpl() }
//    factory<DemoTwo> { DemoImpl() }
    factory { Main(get(), get()) }
}