package com.angelsheaven.demo.utilities

import android.util.Log

/**
 * MyLogger - a handy logger for printing value in debugging
 * @author Quan Nguyen
 */
interface MyLogger{

    /**
     * Printing a message on logcat console
     * @param message to print out
     */
    fun log(message:String){
        val className = this.javaClass.simpleName
        Log.d(className,message)
    }
}
