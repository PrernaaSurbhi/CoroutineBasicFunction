package com.example.coroutinebasicfunction

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.system.measureTimeMillis

//output
//MainActivity  com.example.coroutinebasicfunction   D  network 1
//MainActivity  com.example.coroutinebasicfunction   D  network 2
//MainActivity  com.example.coroutinebasicfunction   D  request took 3008 ms


class MainActivity : AppCompatActivity() {
    val Tag = "MainActivity"
    private lateinit var coroutineText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val ans1 = async { network1() }
                val ans2 = async { network2() }

                Log.d(Tag,ans1.await())
                Log.d(Tag,ans2.await())
            }

            Log.d(Tag,"request took $time ms")
        }
    }

    suspend fun network1(): String{
        delay(3000L)
        return "network 1"
    }

    suspend fun network2(): String{
        delay(3000L)
        return "network 2"
    }


}