package com.example.testtechniqueresto.models


import com.google.gson.annotations.SerializedName

data class Cuisine(
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("character")
    val character: String,
    @SerializedName("type")
    val type: String
)