package com.idrisov.gambit.product_list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.idrisov.gambit.databinding.ItemProductBinding
import com.idrisov.gambit.product_list.domain.entity.ProductEntity
import com.idrisov.gambit.product_list.ui.ProductListAdapter.ProductListViewHolder

class ProductListAdapter(
    private val onClick: (entity: ProductEntity) -> Unit,
): ListAdapter<ProductEntity, ProductListViewHolder>(
    ProductDiffUtil()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListViewHolder {
        return ProductListViewHolder(
            itemBinding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ProductListViewHolder,
        position: Int
    ) {
        holder.bind(currentList[position])
    }

    inner class ProductListViewHolder(
        private val itemBinding: ItemProductBinding,
    ): RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                onClick(currentList[adapterPosition])
            }
        }

        fun bind(entity: ProductEntity) {

            with(itemBinding) {
                logoImageView.load(entity.image)
                nameTextView.text = entity.name
                priceTextView.text = entity.newPrice.toString()
            }
        }

    }

    class ProductDiffUtil: DiffUtil.ItemCallback<ProductEntity>() {

        override fun areItemsTheSame(
            oldItem: ProductEntity,
            newItem: ProductEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
                    && oldItem.newPrice == newItem.newPrice
        }
    }
}