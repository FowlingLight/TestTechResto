package com.example.testtechniqueresto.models


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages_count")
    val pagesCount: Int
)