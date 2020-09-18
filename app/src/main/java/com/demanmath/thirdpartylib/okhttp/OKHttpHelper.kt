package com.demanmath.thirdpartylib.okhttp

import okhttp3.*
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeUnit

/*
* @author   demanmath
* @date     2020/9/18
*/
class OKHttpHelper  private constructor(){

    companion object{
        val instance = OKHttpHelper()
        val okHttpClient  = OkHttpClient.Builder()
            .addInterceptor(TPLLogInterceptor())
            .connectTimeout(60L,TimeUnit.SECONDS)
            .readTimeout(60L,TimeUnit.SECONDS)
            .build()
    }

    fun getRequest(url: String,listener:OKHttpListener):Call{
        var request = Request.Builder()
            .url(url)
            .get()
            .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                listener.onFail(e)
            }

            override fun onResponse(call: Call, response: Response) {
                listener.onSuccess(response.body?.string())
            }
        })
        return call
    }

    fun postRequest(url: String,requestBody: RequestBody,listener:OKHttpListener):Call{
        var request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                listener.onFail(e)
            }

            override fun onResponse(call: Call, response: Response) {
                listener.onSuccess(response.body?.string())
            }
        })
        return call
    }

    interface OKHttpListener{
        fun onFail(e:Exception)
        fun onSuccess(result:String?)
    }
}