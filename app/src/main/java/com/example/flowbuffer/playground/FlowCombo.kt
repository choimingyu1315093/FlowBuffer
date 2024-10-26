package com.example.flowbuffer.playground

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

//Flow 결합
fun main() = runBlocking {
    //zip
    val nums = (1..3).asFlow()
    val strs = flowOf("one", "two", "three")
    nums.zip(strs) { a, b -> "$a -> $b" }
        .collect { println(it) }

    //combine
    val nums2 = (1..3).asFlow().onEach { delay(100) }
    val strs2 = flowOf("one", "two", "three").onEach { delay(200) }
    nums2.combine(strs2) {a, b -> "${a}은(는) $b"}.collect {
        println(it)
    }
}