package com.example.testtechniqueresto.models


import com.google.gson.annotations.SerializedName

data class BaseGson(
    @SerializedName("restaurants")
    val restaurants: List<Restaurant>?,
    @SerializedName("pagination")
    val pagination: Pagination?
)