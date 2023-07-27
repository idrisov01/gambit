package com.idrisov.gambit.product_list.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: String,
    val name: String,
    val description: String?,
    @SerialName("price") val newPrice: Int,
    val oldPrice: Int,
    val image: String,
)
