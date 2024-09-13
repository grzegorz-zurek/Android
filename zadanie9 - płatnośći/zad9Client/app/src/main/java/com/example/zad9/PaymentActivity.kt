package com.example.zad9

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlin.properties.Delegates
import kotlin.random.Random

class PaymentActivity : AppCompatActivity() {

    private lateinit var URL: String
    private lateinit var buttonConfirm: Button
    private lateinit var editTextUsername: EditText
    private lateinit var editTextImie: EditText
    private lateinit var editTextNazwisko: EditText
    private lateinit var textViewPrice: TextView
    private lateinit var listView: ListView
    private lateinit var items: List<String>
    private lateinit var itemsAdapter: ArrayAdapter<String>
    private var price by Delegates.notNull<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        buttonConfirm = findViewById(R.id.btnSendRequest)
        editTextUsername = findViewById(R.id.et_Username)
        editTextImie = findViewById(R.id.et_Imie)
        editTextNazwisko = findViewById(R.id.et_Nazwisko)
        textViewPrice = findViewById(R.id.textPrice)
        listView = findViewById(R.id.listCart)

        URL = intent.extras!!.get("URL").toString()
        items = intent.extras!!.get("list") as ArrayList<String>
        itemsAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
        listView.adapter = itemsAdapter

        price = 0.0
        setPrice()

        buttonConfirm.setOnClickListener {
            onConfirmButtonClick()
        }
    }

    private fun setPrice(){
        price = Random.nextDouble(0.0,5.0+25*items.size)
        textViewPrice.append(" $price")
    }

    private fun onConfirmButtonClick(){
        val username = editTextUsername.text.toString()
        val imie = editTextImie.text.toString()
        val nazwisko = editTextNazwisko.text.toString()

        if(username.isEmpty() || imie.isEmpty() || nazwisko.isEmpty()){
            Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val json = JSONObject()
        json.put("name",username)
        json.put("balance",price)

        var success = false

        val thread = Thread {
            val httpURLConnection: HttpURLConnection = URL(URL + "payment").openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.setRequestProperty("Content-type", "application/json")
            httpURLConnection.setRequestProperty("Accept", "application/json")
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true

            val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
            outputStreamWriter.write(json.toString())
            outputStreamWriter.flush()

            val responseCode = httpURLConnection.responseCode
            success = responseCode == HttpURLConnection.HTTP_ACCEPTED
        }

        thread.start()

        thread.join()

        if(!success){
            Toast.makeText(applicationContext,"Transaction had gone wrong :(", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(applicationContext,"Transaction accepted.", Toast.LENGTH_SHORT).show()

        finish()
    }
}