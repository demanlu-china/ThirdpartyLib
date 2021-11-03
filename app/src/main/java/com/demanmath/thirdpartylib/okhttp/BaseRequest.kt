package com.demanmath.thirdpartylib.okhttp

import com.demanmath.thirdpartylib.base.GsonUtil
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

/*
* @author   demanmath
* @date     2020/9/18
*/
abstract class BaseRequest<T> {

    fun getRequestBody():RequestBody{
        return RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), GsonUtil.gsonInstance.toJson(this))
    }

    fun getUrl():String{
        return "https://www.baidu.com"
    }

    fun get(listener:OKHttpHelper.OKHttpListener){
        OKHttpHelper.instance.getRequest(getUrl(),listener)
    }

    fun post(listener:OKHttpHelper.OKHttpListener){
        OKHttpHelper.instance.postRequest(getUrl(),getRequestBody(),listener)
    }


}