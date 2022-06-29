package com.top.compose.sample.scope

class BugTest {

    fun getBug(){
        throw NullPointerException()
    }
}