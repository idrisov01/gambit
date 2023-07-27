package com.idrisov.gambit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.idrisov.gambit.databinding.ActivityMainBinding
import com.idrisov.gambit.product_list.ui.ProductListFragment

class MainActivity: AppCompatActivity() {

    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        showProductsListFragment()
    }

    private fun showProductsListFragment() {

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.add(
            viewBinding.fragmentContainer.id,
            ProductListFragment.newInstance()
        )

        fragmentTransaction.commit()
    }
}