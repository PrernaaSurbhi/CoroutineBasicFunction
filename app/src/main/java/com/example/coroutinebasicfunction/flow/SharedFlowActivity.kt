package com.example.coroutinebasicfunction.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.coroutinebasicfunction.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SharedFlowActivity : AppCompatActivity() {
    val tag = "SharedFlowActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_flow)

        GlobalScope.launch(Dispatchers.Main){
            val result = producer().collect()
            Log.d(tag,"consumer1 :"+result)
        }

        lifecycleScope.launch(Dispatchers.Main){
            delay(2000L)
            val result = producer().collect()
            Log.d(tag,"consumer2: "+result.toString())
        }

    }

    fun producer(): Flow<Int> {
        //shared flow with reply parameter is for the buffer
        val mutableSharedFlow = MutableSharedFlow<Int>(2)

        GlobalScope.launch {
            val list = listOf(1,2,3,4,5,6,7,8,9)
            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000L)
            }
        }
        return mutableSharedFlow

    }
}