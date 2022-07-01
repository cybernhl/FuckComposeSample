package com.top.compose.sample.scope

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Flow


fun main() = runBlocking {


    //操作符
    //flowwOf,asFlow,map,transform,take,toList,toSet,first,reduce,buffer,collectLast,zip,combine,
    flow {
        (0..4).forEach { emit(it) }
    }.collect {
        println(it)
    }

}