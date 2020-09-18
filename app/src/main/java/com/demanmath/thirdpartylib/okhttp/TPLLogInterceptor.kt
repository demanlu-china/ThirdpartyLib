package com.demanmath.thirdpartylib.okhttp

import com.demanmath.thirdpartylib.base.TraceLog
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/*
* @author   demanmath
* @date     2020/9/18
*/
class TPLLogInterceptor:Interceptor {
    companion object{
        const val TAG = "TPLHttp:"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headers = request.headers
        for(i in 0 until headers.size){
            TraceLog.i(TAG+request.hashCode(),headers.name(i)+":"+headers.value(i))
        }
        val requestBody = request.body
        requestBody?.let {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            val contentType = requestBody.contentType()
            val charset: Charset = contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
            if(buffer.isProbablyUtf8()){
                TraceLog.i(TAG+request.hashCode(),"RequestBody:${buffer.readString(charset)}")
            }
        }

        val response = chain.proceed(request)
        val responseBody = response.body
        TraceLog.i(TAG+request.hashCode(),"ResponseBody:${responseBody?.string()}")

        return response
    }
}