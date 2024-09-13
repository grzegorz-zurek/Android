package com.example.zad5.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import com.example.zad5.R
import com.example.zad5.databinding.CartProductBinding
import com.example.zad5.databinding.ProductBinding
import com.example.zad5.models.Product

class ProductAdapter(private val context: Context, private val products: Array<Product>,private val binding: ViewBinding) : BaseAdapter() {
    override fun getCount(): Int {
        return products.size
    }

    override fun getItem(p0: Int): Any {
        return products[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View
        val product: TextView
        val button: Button
        when (binding) {
            is ProductBinding -> {
                view = LayoutInflater.from(context).inflate(R.layout.product,p2,false)
                product = view.findViewById(R.id.productName)
                button = view.findViewById(R.id.buttonProductDescription)
                val cartButton: Button = view.findViewById(R.id.buttonAddToCart)
                cartButton.tag = p0
            }
            is CartProductBinding -> {
                view = LayoutInflater.from(context).inflate(R.layout.cart_product,p2,false)
                product = view.findViewById(R.id.cartProductName)
                button = view.findViewById(R.id.cartButtonProductDescription)
            }
            else -> {
                throw Exception("Wrong type of binding!")
            }
        }
        button.tag = p0
        product.text = products[p0].name
        return view
    }
}