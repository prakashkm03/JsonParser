package com.example.jsonparser.model

import com.google.gson.annotations.SerializedName

data class Employee(
    var id : Int,
    var info : Student?
    )