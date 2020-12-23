package com.example.testtechniqueresto.models


import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("id")
    val id: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("main_picture")
    val mainPicture: MainPicture,
    @SerializedName("description")
    val description: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("logo")
    val logo: Logo,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("exclusive")
    val exclusive: Boolean,
    @SerializedName("flash_ordering")
    val flashOrdering: Boolean,
    @SerializedName("secondary_images")
    val secondaryImages: List<Any>,
    @SerializedName("price_range")
    val priceRange: Int,
    @SerializedName("preparation_time")
    val preparationTime: PreparationTime,
    @SerializedName("current_preparation_time")
    val currentPreparationTime: Int,
    @SerializedName("address")
    val address: Address,
    @SerializedName("cuisines")
    val cuisines: List<Cuisine>,
    @SerializedName("take_away")
    val takeAway: Boolean,
    @SerializedName("on_site")
    val onSite: Boolean,
    @SerializedName("web_page_url")
    val webPageUrl: String,
    @SerializedName("max_seats")
    val maxSeats: Int,
    @SerializedName("grouped_order_available")
    val groupedOrderAvailable: Boolean,
    @SerializedName("grouped_order_discounts")
    val groupedOrderDiscounts: List<GroupedOrderDiscount>,
    @SerializedName("favorited")
    val favorited: Boolean,
    @SerializedName("available_at")
    val availableAt: String,
    @SerializedName("distance")
    val distance: Double
)