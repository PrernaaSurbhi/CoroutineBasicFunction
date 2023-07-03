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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

//output
//8   ActivityTaskManager     system_process                       I  START u0 {cmp=com.example.coroutinebasicfunction/.flow.FlowActivity} from uid 10146
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onEach about to emit values
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val -55
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onStart is called and started
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onEach about to emit values
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val 1
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onEach about to emit values
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val 2
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onEach about to emit values
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val 3
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onEach about to emit values
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val 4
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onEach about to emit values
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val 5
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onEach about to emit values
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val 6
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onEach about to emit values
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val 7
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onEach about to emit values
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val 8
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onEach about to emit values
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val 9
// FlowActivity            com.example.coroutinebasicfunction   D  flow-collect collected the val -99
// FlowActivity            com.example.coroutinebasicfunction   D  flow-onCompletion is completed

class FlowActivity : AppCompatActivity() {
    val tag = "FlowActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)

        val job = lifecycleScope.launch(Dispatchers.Main) {
            //flowEvents
            producer()
                .onStart {
                    //here we can forcefully added the values
                    //below 55 can be collected as first element of the flow
                    emit(-55)
                    Log.d(tag,"flow-onStart is called and started")
                }

                .onEach {
                    Log.d(tag,"flow-onEach about to emit values")
                }

                .onCompletion {
                    //here also we can add the values
                    ////below 99 can be collected as last element of the flow
                    emit(-99)
                    Log.d(tag,"flow-onCompletion is completed")
                }

                .collect{
                    Log.d(tag,"flow-collect collected the val "+it.toString())
                }

        }

//        CoroutineScope(Dispatchers.Main).launch{
//            delay(4000L)
//            job.cancel()
//        }


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