package com.idrisov.gambit.product_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.idrisov.gambit.App
import com.idrisov.gambit.databinding.FragmentProductListBinding
import com.idrisov.gambit.product_detail.ProductDetailBottomSheet
import com.idrisov.gambit.product_list.data.repository.ProductListRepositoryImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProductListFragment: Fragment() {

    companion object {

        fun newInstance(): ProductListFragment {
            return ProductListFragment()
        }
    }

    private val viewBinding by lazy {
        FragmentProductListBinding.inflate(layoutInflater)
    }

    private val viewModel = ProductListViewModel(
        repository = ProductListRepositoryImpl(
            restApi = App.restApi
        )
    )

    private val productsAdapter by lazy {
        ProductListAdapter(
            onClick = { entity ->
                ProductDetailBottomSheet
                    .newInstance(entity)
                    .show(childFragmentManager, ProductDetailBottomSheet::class.simpleName)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {

        viewBinding.productsRecyclerView.adapter = productsAdapter

        viewModel.productsFlow
            .onEach { list -> productsAdapter.submitList(list) }
            .launchIn(lifecycleScope)
    }

}