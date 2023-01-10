package com.example.koindemo.utils

import android.util.Log

interface DemoOne{
    fun demoOne()
}

interface DemoTwo{
    fun demoTwo()
}

class DemoImpl:DemoOne,DemoTwo{
    override fun demoOne() {
        Log.d("Demo--->","One")
    }

    override fun demoTwo() {
        Log.d("Demo--->","Two")

    }


}
class Main(val demoOne:DemoOne,val demoTwo: DemoTwo){
    fun getDemo(){
        demoOne.demoOne()
        demoTwo.demoTwo()
    }
}