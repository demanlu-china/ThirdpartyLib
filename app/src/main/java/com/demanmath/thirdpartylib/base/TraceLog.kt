package com.demanmath.thirdpartylib.base

import android.util.Log
import java.util.*
import kotlin.text.StringBuilder

class TraceLog {
    private class LogLine(val tag:String,val text:String)

    companion object{
        private const val preTag = "ThirdPartyLib."

        var isLogOn = true

        @JvmStatic
        private  fun concatLogLine(extraMessage:String): LogLine {
            val steArray = Throwable().stackTrace
            var ste:StackTraceElement = steArray[2]
            for(i in 2 until steArray.size){
                ste = steArray[i]
                if(!ste.className.contains(TraceLog::class.java.simpleName)){
                    break
                }
            }

            val shortClassName = ste.className.substring(ste.className.lastIndexOf(".")+1)
            val logText = String.format(
                Locale.US, "%s:%s [at (%s:%d)]",
                ste.methodName,
                extraMessage,
                ste.fileName,
                ste.lineNumber
            )
            return LogLine(preTag +shortClassName,logText)
        }

        @JvmStatic
        fun getTag(tag:String):String{
            return preTag +tag
        }

        @JvmStatic
        fun v(tag: String,msg:String = ""){
            if(!isLogOn){
                return
            }
            Log.v(getTag(tag), msg)
        }

        @JvmStatic
        fun d(tag: String,msg:String = ""){
            if(!isLogOn){
                return
            }
            Log.d(getTag(tag), msg)
        }

        @JvmStatic
        fun i(tag: String,msg:String = ""){
            if(!isLogOn){
                return
            }
            Log.i(getTag(tag), msg)
        }

        @JvmStatic
        fun w(tag: String,msg:String = ""){
            if(!isLogOn){
                return
            }
            Log.w(getTag(tag), msg)
        }

        @JvmStatic
        fun e(tag: String,msg:String = ""){
            if(!isLogOn){
                return
            }
            Log.e(getTag(tag), msg)
        }

        @JvmStatic
        fun v(msg:String = ""){
            if(!isLogOn){
                return
            }
            val logLine = concatLogLine(msg)
            Log.v(logLine.tag, logLine.text)
        }

        @JvmStatic
        fun d(msg:String = ""){
            if(!isLogOn){
                return
            }
            val logLine = concatLogLine(msg)
            Log.d(logLine.tag, logLine.text)
        }

        @JvmStatic
        fun i(msg:String = ""){
            if(!isLogOn){
                return
            }
            val logLine = concatLogLine(msg)
            Log.i(logLine.tag, logLine.text)
        }

        @JvmStatic
        fun w(msg:String = ""){
            if(!isLogOn){
                return
            }
            val logLine = concatLogLine(msg)
            Log.w(logLine.tag, logLine.text)
        }

        @JvmStatic
        fun e(msg:String = ""){
            if(!isLogOn){
                return
            }
            val logLine = concatLogLine(msg)
            Log.e(logLine.tag, logLine.text)
        }

        @JvmStatic
        fun i(){
            if(!isLogOn){
                return
            }
            val logLine = concatLogLine("")
            Log.i(logLine.tag, logLine.text)
        }

        @JvmStatic
        fun i(collection: Collection<*>){
            var str = StringBuilder("[")
            collection.forEach {
                str.append(it.toString())
                str.append(",")
            }
            str.append("]")
            val logLine = concatLogLine(str.toString())
            Log.i(logLine.tag, logLine.text)
        }
    }
}