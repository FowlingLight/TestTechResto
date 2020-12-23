package com.example.testtechniqueresto.models


import com.google.gson.annotations.SerializedName

data class GroupedOrderDiscount(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nb_people")
    val nbPeople: Int,
    @SerializedName("discount_percent")
    val discountPercent: Int,
    @SerializedName("minimum_amount")
    val minimumAmount: Int,
    @SerializedName("delta_people")
    val deltaPeople: Int
)