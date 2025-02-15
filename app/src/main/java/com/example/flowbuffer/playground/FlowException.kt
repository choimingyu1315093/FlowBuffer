package com.example.flowbuffer.playground

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

//Flow 예외처리
fun simple2(): Flow<Int> = flow {
    for(i in 1..3){
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    try {
        simple2().collect { value ->
            println(value)
            check(value <= 1) { "Collected $value"}
        }
    }catch (e: Throwable){
        println("Caught $e")

    }
}