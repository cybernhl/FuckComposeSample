package com.top.compose.sample.scope

import kotlinx.coroutines.*
import kotlin.concurrent.thread

//fun main() {
//
//    //test()
//    //test2()
//    runBlockingTest()
//}

fun main() = runBlocking {
    println("main runBlocking start")

    //testJoin()
    //test3()
    //test4(this)
    test5()

    Thread.sleep(10000)
    println("main runBlocking end")
}

fun test5() {

    GlobalScope.launch {
        val testSuspendFun1 = testSuspendFun1()
        val testSuspendFun2 = testSuspendFun2()

        println("testSuspendFun1=$testSuspendFun1,testSuspendFun2=$testSuspendFun2")
    }
}

suspend fun testSuspendFun1(): Int {

    println("testSuspendFun1 start")
    delay(3000)
    println("testSuspendFun1 end")
    return 20
}

suspend fun testSuspendFun2(): Int {
    println("testSuspendFun2 start")
    delay(3000)
    println("testSuspendFun2 end")
    return 200
}

//CoroutineScope协程作用域
//使用同一协程作用域开启的多个子协程，协程都会等待该协程作用域中的子协程全部执行完后，才会结束
fun test4(coroutineScopeeeee: CoroutineScope) {
    println("test4 coroutineScope start")

    coroutineScopeeeee.launch(Dispatchers.IO) {
        delay(2000)
        println("test4 coroutineScope launch...")
    }
    println("test4 coroutineScope end")

}

suspend fun test3() {

    val job = GlobalScope.launch { // root coroutine with launch
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException() // 我们将在控制台打印 Thread.defaultUncaughtExceptionHandler
    }
    job.join()
    println("Joined failed job")
    val deferred = GlobalScope.async { // root coroutine with async
        println("Throwing exception from async")
        throw ArithmeticException() // 没有打印任何东西，依赖用户去调用等待
    }
    try {
        deferred.await()
        println("Unreached")
    } catch (e: ArithmeticException) {
        println("Caught ArithmeticException")
    }
}

suspend fun testJoin() {
    println("testJoin start")

    val job = GlobalScope.launch {

        delay(2000)
        println("testJoin launch....")

    }
    println("testJoin join")

    job.join()

    println("testJoin end")

}

//阻塞式协程
fun runBlockingTest() {
    println("runBlockingTest start")

    runBlocking {
        delay(2000)
        println("runBlocking")
    }
    //下面代码均被runBlocking阻塞
    println("runBlockingTest end")

}

//使用Kotlin优雅开启线程
fun test2() {
    thread {
        println("使用Kotlin优雅开启线程")
    }
}

//使用GlobalScope.launch开启协程，若协程执行时，线程已经结束，则该协程内的代码块不会被执行
//
fun test() {
    println("GlobalScope.test start")
    GlobalScope.launch {
        delay(2000)
        println("GlobalScope.launch")
    }
    //Thread.sleep(5000) //   协程可执行
    //Thread.sleep(1000) //协程不可执行
    println("GlobalScope.test end")
}
