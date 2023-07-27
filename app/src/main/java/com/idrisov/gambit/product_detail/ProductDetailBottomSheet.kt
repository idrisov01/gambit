package com.idrisov.gambit.product_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.idrisov.gambit.databinding.BottomSheetProductDetailBinding
import com.idrisov.gambit.product_list.domain.entity.ProductEntity
import kotlin.math.roundToInt

class ProductDetailBottomSheet: BottomSheetDialogFragment() {

    companion object {

        fun newInstance(entity: ProductEntity): ProductDetailBottomSheet {

            val fragment = ProductDetailBottomSheet()

            val bundle = Bundle()

            bundle.putParcelable("ARG_ENTITY", entity)

            fragment.arguments = bundle

            return fragment
        }
    }

    private val viewBinding by lazy {
        BottomSheetProductDetailBinding.inflate(layoutInflater)
    }

    private val productEntity by lazy {
        arguments?.getParcelable("ARG_ENTITY") as? ProductEntity
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
        setupDialog()
        setupData()
    }

    private fun setupDialog() {

        val bottomSheet = (dialog as? BottomSheetDialog)
            ?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        val bottomHeight = (resources.displayMetrics.heightPixels * 0.8).roundToInt()

        if (bottomSheet is FrameLayout) {
            bottomSheet.layoutParams?.height = bottomHeight
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun setupData() {

        with(viewBinding) {
            imageView.load(productEntity?.image)
            nameTextView.text = productEntity?.name
            priceTextView.text = productEntity?.newPrice.toString()
            descriptionTextView.text = productEntity?.description
        }
    }
}