package com.example.jsonparser.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("key_name")
    var name : String?,
    @SerializedName("key_address")
    var address : String?
)