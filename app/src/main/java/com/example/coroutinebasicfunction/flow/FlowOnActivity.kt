package com.example.coroutinebasicfunction.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutinebasicfunction.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class FlowOnActivity : AppCompatActivity() {

    val tag = "FlowOnActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_on)

        GlobalScope.launch(Dispatchers.Main){
            producer()
                .flowOn(Dispatchers.IO)
                .collect{
                    Log.d(tag,"consumer thread ${Thread.currentThread().name}")

                }
        }
    }

    fun producer(): Flow<Int>{
        return flow<Int>{
            listOf(1,2,3,4,5,6,7,8,9).also {
               it.forEach {
                   delay(1000L)
                   Log.d(tag,"producer thread ${Thread.currentThread().name}")
                   emit(it)
               }
           }
        }
    }
}