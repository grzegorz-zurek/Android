package com.example.zad5

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.zad5.adapters.ProductAdapter
import com.example.zad5.databinding.ActivityMainBinding
import com.example.zad5.databinding.ProductBinding
import com.example.zad5.models.Product

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var productList: Array<Product>
    private lateinit var cart: MutableSet<Product>
    private lateinit var adapter: ProductAdapter
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val productNames  = resources.getStringArray(R.array.ProductNames)
        val productDescriptions = resources.getStringArray(R.array.ProductDescriptions)
        productList = Array(productNames.size) {i -> Product(productNames[i],productDescriptions[i])}
        cart = LinkedHashSet()
        adapter = ProductAdapter(this,productList,ProductBinding.inflate(layoutInflater))
        listView = mainBinding.products
        listView.adapter = adapter

        mainBinding.buttonCart.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("CartContent",cart.toTypedArray())
            val cartActivity = Intent(this, SecondActivity::class.java)
            cartActivity.putExtra("List",bundle)
            startActivity(cartActivity)
        }
    }

    fun productDescClick(view: View){
        if(view !is Button) throw Exception("???")
        val bundle = Bundle()
        val p0: Int = Integer.parseInt(view.tag.toString())
        val productDescriptionActivity = Intent(this, DescriptionActivity::class.java)
        bundle.putSerializable("Product",productList[p0])
        productDescriptionActivity.putExtra("Product",bundle)
        startActivity(productDescriptionActivity)
    }

    fun addToCart(view: View){
        if(view !is Button) throw Exception("???")
        val p0 = Integer.parseInt(view.tag.toString())
        cart.add(productList[p0])
    }
}