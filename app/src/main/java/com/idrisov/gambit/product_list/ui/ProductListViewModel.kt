package com.idrisov.gambit.product_list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idrisov.gambit.product_list.domain.entity.ProductEntity
import com.idrisov.gambit.product_list.domain.repotisory.ProductListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val repository: ProductListRepository
): ViewModel() {

    init {
        getListProduct()
    }

    val productsFlow = MutableStateFlow<List<ProductEntity>?>(null)

    private fun getListProduct() {

        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getListProducts()
            productsFlow.emit(list)
        }
    }
}