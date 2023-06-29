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

// outside -thread is :main
// E  coroutine -thread is :DefaultDispatcher-worker-1


class MainActivity : AppCompatActivity() {
    val Tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            delay(1000)
            Log.e(Tag,"coroutine -thread is :${Thread.currentThread().name}")
        }

        Log.e(Tag,"outside -thread is :${Thread.currentThread().name}")
    }

}