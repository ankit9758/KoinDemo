package com.example.koindemo.di.modules

import android.util.Log
import com.example.koindemo.ui.activity.MainActivity
import org.koin.dsl.module

class ComponentA(){
    fun getA(){
        Log.d("Scope--->","A")
    }
}

