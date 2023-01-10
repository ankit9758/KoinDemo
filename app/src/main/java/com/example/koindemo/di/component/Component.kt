package com.example.koindemo.di.component

import com.example.koindemo.utils.Main
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Component:KoinComponent {
    val main :Main by inject()
}