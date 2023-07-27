package com.idrisov.gambit

import com.idrisov.gambit.product_list.data.dto.ProductDto
import retrofit2.http.GET

interface RestApi {

    @GET("category/5")
    suspend fun getProductsList(): List<ProductDto>
}