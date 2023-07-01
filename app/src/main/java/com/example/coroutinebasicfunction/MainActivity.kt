package com.example.coroutinebasicfunction

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val Tag = "MainActivity"
    private lateinit var coroutineText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coroutineText = findViewById(R.id.coroutine_text)

        GlobalScope.launch(Dispatchers.IO){
            Log.d(Tag,"starting coroutine: +${Thread.currentThread().name}")
            val nwkText =  nktCall()

            //withcontext(Dispacther.Io) -- it will help to switch the thread on coroutine

            withContext(Dispatchers.Main){
                Log.d(Tag,"receiving coroutine: +${Thread.currentThread().name}")
                coroutineText.text = nwkText
            }

        }
    }

    suspend fun nktCall():String{
        delay(3000L)
        return "network call 1"
    }


}