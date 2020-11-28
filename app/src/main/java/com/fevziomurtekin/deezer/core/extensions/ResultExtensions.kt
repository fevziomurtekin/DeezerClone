package com.fevziomurtekin.deezer.core.extensions

import com.fevziomurtekin.deezer.core.data.ApiResponse
import com.fevziomurtekin.deezer.core.data.ApiResult
import com.fevziomurtekin.deezer.core.data.DaoResult

fun ApiResponse<*>?.isSuccesAndNotNull():Boolean = this?.let {
    it.data != null && it.isSucces
} ?: run {
    false
}

fun ApiResult<*>?.isSuccessAndNotNull() =
    this is ApiResult.Success && this.data != null


fun DaoResult?.isSuccessAndNotNull() = this?.let {
    it.data != null && it.isSucces
} ?: run {
   false
}

fun ApiResult<*>?.getResult(): Any? =  when(this) {
    is ApiResult.Success -> this.data
    else -> null
}