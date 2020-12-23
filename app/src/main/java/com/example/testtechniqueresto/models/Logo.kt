package com.example.testtechniqueresto.models


import com.google.gson.annotations.SerializedName

data class Logo(
    @SerializedName("url")
    val url: String,
    @SerializedName("key")
    val key: String
)