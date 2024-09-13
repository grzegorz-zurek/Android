package com.example.zad5

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.zad5.databinding.ActivityDescriptionBinding
import com.example.zad5.models.Product

class DescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptionBinding
    private lateinit var product: Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras: Bundle? = intent.getBundleExtra("Product")
        product = if(extras != null){
            extras.get("Product") as Product
        }else{
            Product("","")
        }
        binding.nameProduct.text = "Opis " + product.name + ":"
        binding.productDescription.text = product.desc

    }

    fun backClickDesc(view: View){
        setResult(RESULT_OK)
        finish()
    }
}