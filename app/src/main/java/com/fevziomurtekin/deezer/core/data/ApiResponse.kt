package com.fevziomurtekin.deezer.core.data

data class ApiResponse<out T>(
    val isSucces: Boolean,
    val data: T?,
    val errorMessage: String?
)