package com.example.coroutinebasicfunction

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class MainActivity : AppCompatActivity() {
    val Tag = "MainActivity"
    private lateinit var coroutineText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coroutineText = findViewById(R.id.coroutine_text)

       val job =  GlobalScope.launch(Dispatchers.Default) {
           Log.e(Tag,"starting long running process")

           //withTimeOut is a suspension function which will cancel the coroutine after specific time ,
           // as it is hard to handle job.cancel some time it provide coroutine scope.
           withTimeout(3000L){
               for(i in 30..40){
                   if(isActive){
                       Log.e(Tag,"i am long running process")
                   }
               }
           }

           Log.e(Tag,"i am long running process")

        }

    }


}