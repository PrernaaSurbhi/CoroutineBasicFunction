package com.example.coroutinebasicfunction

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//output
//result receive: Result 1 after 1688934218220ms
//result receive: Result 2 after 1688934218220ms
//resultList [Result 1, Result 2] after 1688934218220 ms

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val resultMutableList = mutableListOf<String>()

    val deferred1 = async {
        val result1 =  delayNkCall(1)
        resultMutableList.add(result1)
        println("result receive: $result1 after ${(startTime) }ms")
        result1
    }

    val deferred2 = async {
        val result2 =  delayNkCall(2)
        resultMutableList.add(result2)
        println("result receive: $result2 after ${(startTime) }ms")
        result2
    }

    deferred1.await()    //deferred1.await() ---> await fun is used
                                     // to get value from defereed onhiect

    deferred2.await()

    val result = listOf( deferred1.await(),deferred2.await() )

    println("resultList $result after $startTime ms")
}

suspend fun delayNkCall(number:Int):String{
    delay(5000)
    return "Result $number"
}



