package com.example.coroutinebasicfunction.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.coroutinebasicfunction.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

//output
// FlowActivity            com.example.coroutinebasicfunction   D  1
//FlowActivity             com.example.coroutinebasicfunction   D  2
//FlowActivity             com.example.coroutinebasicfunction   D  3

class FlowActivity : AppCompatActivity() {
    val tag = "FlowActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        val job = lifecycleScope.launch(Dispatchers.Main) {
            val data: Flow<Int> = producer()
            data.collect(){
                Log.d(tag,it.toString())
            }
        }

        CoroutineScope(Dispatchers.Main).launch{
            delay(4000L)
            job.cancel()
        }


    }

    private fun producer() = flow<Int> {
        listOf(1, 2,3, 4, 5, 6, 7, 8, 9).run {
            forEach {
                delay(1000L)
                emit(it)
            }
        }
    }
}