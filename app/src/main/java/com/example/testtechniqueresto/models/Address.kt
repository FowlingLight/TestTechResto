package com.example.testtechniqueresto.models


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("street")
    val street: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("zip")
    val zip: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("formated")
    val formated: String
)