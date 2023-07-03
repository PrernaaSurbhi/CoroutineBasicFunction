package com.example.coroutinebasicfunction

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutinebasicfunction.flow.FlowOnActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//output
//MainActivity            com.example.coroutinebasicfunction   D  still running
//MainActivity            com.example.coroutinebasicfunction   D  still running
//MainActivity            com.example.coroutinebasicfunction   D  still running
//MainActivity            com.example.coroutinebasicfunction   D  still running
//MainActivity            com.example.coroutinebasicfunction   D  still running

//here first coroutine survive till life cycle of activity.
// once activity end after second coroutine start , lifecycleScope coroutine will also end.


class MainActivity : AppCompatActivity() {
    val Tag = "MainActivity"
    private lateinit var button: Button
    private lateinit var flowButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.coroutine_button)
        flowButton = findViewById(R.id.flow_button)

        flowButton.setOnClickListener {
            Intent(this@MainActivity, FlowOnActivity::class.java).also{
                startActivity(it)
                finish()
            }
        }

        button.setOnClickListener {
           lifecycleScope.launch{
               while (true){
                   delay(1000L)
                   Log.d(Tag,"still running")
               }
           }

          GlobalScope.launch {
              delay(5000L)
              Intent(this@MainActivity,SecondActivity::class.java).also{
                  startActivity(it)
                  finish()
              }
          }
        }
    }

}