package com.example.coroutinebasicfunction

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.coroutinebasicfunction.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//output
// I  network call 1
// I  network call 2
class MainActivity : AppCompatActivity() {
    val Tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
          val firstNetworkCallAnswer = nkt1()
            println(firstNetworkCallAnswer)

            val secondNetworkCallAnswer = nk2()
            println(secondNetworkCallAnswer)

        }

    }

    suspend fun nkt1():String{
        delay(3000L)
        return "network call 1"
    }

    suspend fun nk2():String{
        delay(3000L)
        return "network call 2"
    }

}