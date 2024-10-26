package com.example.flowbuffer.playground

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


//flow 버퍼링
//데이터를 만드는 생산자와 소비자의 속도가 같을 수 없다. 그래서 나온게 버퍼링이다.

//생산자
fun simple(): Flow<Int> = flow {
    for(i in 1..3){
        delay(100L)
        emit(i)
    }
}

//소비자
fun main() = runBlocking {
    val time = measureTimeMillis {
        simple().buffer().collect { value -> //buffer가 없으면 생산자에서 숫자를 보내면 300L뒤에 print하고 다시 생산자에서 숫자를 보낸다. buffer가 있으면 생산자에서 숫자를 보내면 300L뒤에가 아니라 바로 생산자에서 숫자를 보낸다.
            delay(300L)
            println(value)
        }
    }
    println("Collected in $time ms")
}
