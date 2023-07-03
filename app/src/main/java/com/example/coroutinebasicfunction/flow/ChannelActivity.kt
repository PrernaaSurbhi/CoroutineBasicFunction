package com.example.coroutinebasicfunction.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.coroutinebasicfunction.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

//output
//ChannelActivity         com.example.coroutinebasicfunction   D  2
//ChannelActivity         com.example.coroutinebasicfunction   D  3

class ChannelActivity : AppCompatActivity() {
    //initialization of kotlin channel
    var channel = Channel<Int>()
    val tag = "ChannelActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel)
        producer()
        consumer()

    }

    fun producer(){
        lifecycleScope.launch(Dispatchers.IO){
            channel.send(2)
            channel.send(3)
        }
    }

    fun consumer(){
        lifecycleScope.launch(Dispatchers.Main){
            Log.d(tag,channel.receive().toString())
            Log.d(tag,channel.receive().toString())
        }
    }

}