package com.example.zad9

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ProductListActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var items: Array<String>
    lateinit var resultItems: MutableList<String>
    lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        items = resources.getStringArray(R.array.Products)
        resultItems = ArrayList()

        val itemsAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
        listView = findViewById(R.id.listProducts)
        listView.adapter = itemsAdapter
        listView.setOnItemClickListener { _, _, i, _ -> resultItems.add(listView.adapter.getItem(i).toString()) }

        backButton = findViewById(R.id.btnBack)

        backButton.setOnClickListener {
            onBackButtonClick()
        }
    }

    private fun onBackButtonClick(){
        val intent = Intent()
        intent.putExtra("list",resultItems.toTypedArray())
        setResult(RESULT_OK,intent)
        finish()
    }
}