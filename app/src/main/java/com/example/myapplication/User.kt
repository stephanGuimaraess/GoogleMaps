package com.example.myapplication

class User(

    val name: String,
    val age: Int,
    val preferences: ArrayList<String>,
    var currentStoreLocation: UserLocation? = null

)