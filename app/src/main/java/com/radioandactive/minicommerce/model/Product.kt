package com.radioandactive.minicommerce.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("name")
    val title: String,

    @SerializedName("photo_url")
    val photoUrl: String,

    val price: Double,

    val description: String,

    val isOnSale: Boolean
)