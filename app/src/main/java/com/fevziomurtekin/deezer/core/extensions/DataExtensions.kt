package com.fevziomurtekin.deezer.core.extensions



fun Any?.isNull(): Boolean = this == null

fun Any?.isNotNull(): Boolean = !this.isNull()

fun Any?.isNull(
    block: ()-> Unit
): Boolean {

    if(this == null) {
        block.invoke()
        return true
    }

    return false
}

fun Any?.isNotNull(
    block: () -> Unit
): Boolean {
    if(this != null) {
        block.invoke()
        return true
    }

    return false
}



