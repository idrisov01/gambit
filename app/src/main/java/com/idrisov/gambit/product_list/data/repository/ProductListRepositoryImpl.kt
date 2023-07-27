package com.idrisov.gambit.product_list.data.repository

import com.idrisov.gambit.RestApi
import com.idrisov.gambit.product_list.domain.entity.ProductEntity
import com.idrisov.gambit.product_list.domain.repotisory.ProductListRepository

class ProductListRepositoryImpl(
    private val restApi: RestApi
): ProductListRepository {

    override suspend fun getListProducts(): List<ProductEntity> {
        return restApi.getProductsList().map { productDto ->
            ProductEntity(
                id = productDto.id,
                name = productDto.name,
                description = productDto.description.orEmpty(),
                newPrice = productDto.newPrice,
                oldPrice = productDto.oldPrice,
                image = productDto.image
            )
        }
    }

}