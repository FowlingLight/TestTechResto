package com.example.testtechniqueresto.models


import com.google.gson.annotations.SerializedName

data class MainPicture(
    @SerializedName("url")
    val url: String,
    @SerializedName("key")
    val key: String
)