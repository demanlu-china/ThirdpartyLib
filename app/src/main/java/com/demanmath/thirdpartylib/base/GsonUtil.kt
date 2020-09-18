package com.demanmath.thirdpartylib.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/*
* @author   demanmath
* @date     2020/9/18
*/
class GsonUtil {
    companion object{
        val gsonInstance = GsonBuilder().create()
    }
}