package com.example.testtechniqueresto.models


import com.google.gson.annotations.SerializedName

data class PreparationTime(
    @SerializedName("min_preparation_time")
    val minPreparationTime: Int,
    @SerializedName("max_preparation_time")
    val maxPreparationTime: Int
)