package com.example.coroutinebasicfunction

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

        val job = GlobalScope.launch(Dispatchers.Default) {

            repeat(4){  //repeat is a kotlin inline function
                Log.e(Tag,"job is getting repeated")
                delay(1000L)
            }
        }

        runBlocking {
             delay(2000L)
            job.cancel()      // this is used to cancel the coroutine 
            Log.e(Tag," main thread is cancel")
        }

    }


}