package com.example.flowbuffer.playground

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

//onCompletion은 성공해야 실행되는게 아니라 완료되면 실행된다.
fun simple3(): Flow<Int> = (1..3).asFlow()
fun main() = runBlocking {
    simple3()
        .map {
            if(it > 2){
                throw Exception("Error")
            }
            it + 1
        }
        .catch { emit(-99) }
        .onCompletion {
            println("Done")
        }
        .collect {value ->
            println(value)
        }
}