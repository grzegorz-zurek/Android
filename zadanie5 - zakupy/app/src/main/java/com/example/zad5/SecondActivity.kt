package com.example.zad5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.zad5.adapters.ProductAdapter
import com.example.zad5.databinding.ActivitySecondBinding
import com.example.zad5.databinding.CartProductBinding
import com.example.zad5.models.Product

class SecondActivity: Activity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var productsInCart : Array<Product>
    private lateinit var adapter: ProductAdapter
    private lateinit var listView: ListView
    private lateinit var cartProductBinding: CartProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        cartProductBinding = CartProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras: Bundle? = intent.getBundleExtra("List")
        productsInCart = if(extras != null){
            extras.get("CartContent") as Array<Product>
        }else{
            emptyArray()
        }
        adapter = ProductAdapter(this,productsInCart,CartProductBinding.inflate(layoutInflater))
        listView = binding.cartProducts
        listView.adapter = adapter

    }

    fun cartProductDescriptionClick(view: View){
        if(view !is Button) throw Exception("???")
        val bundle = Bundle()
        val p0: Int = Integer.parseInt(view.tag.toString())
        val productDescriptionActivity = Intent(this, DescriptionActivity::class.java)
        bundle.putSerializable("Product",productsInCart[p0])
        productDescriptionActivity.putExtra("Product",bundle)
        startActivity(productDescriptionActivity)
    }

    fun backClick(view: View){
        setResult(RESULT_OK)
        finish()
    }
}