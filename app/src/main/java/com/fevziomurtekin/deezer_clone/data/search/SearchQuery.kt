package com.fevziomurtekin.deezer_clone.data.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.*
import kotlin.random.Random

@Entity
@Serializable
data  class SearchQuery (
    @PrimaryKey
    var id:String= java.util.UUID.randomUUID().toString(),
    var q:String?="",
    var time:Long=System.currentTimeMillis()
)
