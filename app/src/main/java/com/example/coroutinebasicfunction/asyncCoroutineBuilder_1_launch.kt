package com.example.coroutinebasicfunction

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//output
//result receive: Result 1 after 1688933606272ms
//result receive: Result 2 after 1688933606272ms
//resultList [Result 1, Result 2] after 1688933606272 ms

//drawback of this technology is use of mutableList

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val resultMutableList = mutableListOf<String>()

    val job1 = launch {
      val result1 =  delayNwkCall(1)
        resultMutableList.add(result1)
        println("result receive: $result1 after ${(startTime) }ms")
    }

    val job2 = launch {
        val result2 =  delayNwkCall(2)
        resultMutableList.add(result2)
        println("result receive: $result2 after ${(startTime) }ms")
    }

    job1.join()  // it will make coroutine job1 suspended until work will be complete
    job2.join()

    println("resultList $resultMutableList after $startTime ms")
}

suspend fun delayNwkCall(number:Int):String{
    delay(5000)
    return "Result $number"
}


