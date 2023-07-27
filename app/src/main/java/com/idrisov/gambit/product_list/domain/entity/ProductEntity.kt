package com.idrisov.gambit.product_list.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductEntity(
    val id: String,
    val name: String,
    val description: String,
    val newPrice: Int,
    val oldPrice: Int,
    val image: String
): Parcelable
