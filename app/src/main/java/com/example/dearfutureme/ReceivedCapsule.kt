package com.example.dearfutureme

data class ReceivedCapsule(
    val fId : Int,
    val title : String,
    val message : String,
    val content : String,
    val receiver_email : String,
    val schedule_open_at : String
)
