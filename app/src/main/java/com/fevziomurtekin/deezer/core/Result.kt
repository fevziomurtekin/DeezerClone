package com.fevziomurtekin.deezer.core


sealed class Result<out R>{
    data class Succes<out T>(val data:T) : Result<T>()
    object Loading : Result<Nothing>()
    object Error : Result<Nothing>()
}