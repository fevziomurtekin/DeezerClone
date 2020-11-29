package com.fevziomurtekin.deezer.data.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data  class SearchQuery (
    @PrimaryKey
    var searchId:String= java.util.UUID.randomUUID().toString(),
    var q:String?="",
    var time:Long=System.currentTimeMillis()
)
