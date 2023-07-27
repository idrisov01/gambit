package com.idrisov.gambit.product_list.domain.repotisory

import com.idrisov.gambit.product_list.domain.entity.ProductEntity

interface ProductListRepository {

    suspend fun getListProducts(): List<ProductEntity>
}