package com.example.coroutinebasicfunction

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    val Tag = "MainActivity"
    private lateinit var coroutineText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coroutineText = findViewById(R.id.coroutine_text)

        Log.d(Tag,"before coroutine")

        runBlocking {
            Log.e(Tag,"cor start: ${Thread.currentThread().name}")

            //we can switch the thread inside the runBlocking with the help of launch() function

            launch(Dispatchers.IO){
                delay(3000L)
                Log.e(Tag,"finished coroutine 1 launch scope: ${Thread.currentThread().name}")
            }

            launch(Dispatchers.IO){
                delay(4000L)
                Log.e(Tag,"finished coroutine 2")
            }

            Log.e(Tag,"start of running block: ${Thread.currentThread().name}")
            delay(3000L)
            Log.e(Tag,"end of running block")
        }

        Log.e(Tag,"after running block")

    }


}