package com.top.compose.sample

class BugTest {

    fun getBug(){
        throw NullPointerException()
    }
}