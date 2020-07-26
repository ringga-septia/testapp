package com.ringga.myapplication.models

data class LoginResponse(
    val akses:Int,
    val message: String,
    val data: User,
    val data2: UserDosen,
    val role : String
    )
