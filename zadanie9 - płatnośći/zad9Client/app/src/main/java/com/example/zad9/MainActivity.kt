package com.example.zad9

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var URL = "/" // <- url z ngroka z backslashem /

    private lateinit var buttonProducts: Button
    private lateinit var buttonPayment: Button
    private lateinit var buttonAddUser: Button
    private lateinit var listView: ListView
    private lateinit var items: MutableList<String>
    private lateinit var itemsAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonProducts = findViewById(R.id.btnListaProduktow)
        buttonPayment = findViewById(R.id.btnPayment)
        buttonAddUser = findViewById(R.id.btnAddUser)

        items = ArrayList()
        itemsAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
        listView = findViewById(R.id.listView)
        listView.adapter = itemsAdapter

        buttonProducts.setOnClickListener {
            onProductButtonClick()
        }
        buttonPayment.setOnClickListener {
            onPaymentButtonClick()
        }
        buttonAddUser.setOnClickListener {
            onAddUserButtonClick()
        }
    }

    private fun onProductButtonClick(){
        startForResult.launch(Intent(this,ProductListActivity::class.java))
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val data: Array<String> = intent!!.getSerializableExtra("list") as Array<String>
            for(item in data){
                if(!items.contains(item))
                    items.add(item)
            }
            itemsAdapter.notifyDataSetChanged()
        }
    }

    private fun onPaymentButtonClick(){
        val intent = Intent(this,PaymentActivity::class.java)
        intent.putExtra("URL",URL)
        intent.putExtra("list",items as ArrayList<String>)
        startActivity(intent)
    }

    private fun onAddUserButtonClick(){
        val intent = Intent(this,AddUserActivity::class.java)
        intent.putExtra("URL",URL)
        startActivity(intent)
    }
}