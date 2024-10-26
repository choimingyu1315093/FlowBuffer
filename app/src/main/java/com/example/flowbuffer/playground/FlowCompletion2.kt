package com.example.flowbuffer.playground

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

fun simple4(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

fun main() = runBlocking {
    simple4()
        .onCompletion { cause ->
            if(cause != null){
                println("Flow completed exceptionally")
            }else {
                println("Flow completed")
            }
        }
        .catch { cause ->
            println("Caught $cause")
        }
        .collect { value ->
            println(value)
        }
}