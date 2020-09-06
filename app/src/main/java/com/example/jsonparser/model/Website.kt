package com.example.jsonparser.model

import javax.xml.transform.Source

data class Website(
    var status : String?,
    var sources : List<Sources>
)