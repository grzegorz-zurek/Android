package com.example.zad9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class AddUserActivity : AppCompatActivity() {
    private lateinit var URL: String
    private lateinit var editTextUsername: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        editTextUsername = findViewById(R.id.addUsername)
        buttonAdd = findViewById(R.id.btnAddUser)
        buttonBack = findViewById(R.id.btnBackToMain)

        URL = intent.getStringExtra("URL").toString()

        buttonAdd.setOnClickListener {
            onAddButtonClick()
        }
        buttonBack.setOnClickListener {
            onBackButtonClick()
        }
    }

    private fun onAddButtonClick(){
        val username = editTextUsername.text.toString()

        if(username.isEmpty()){
            Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val json = JSONObject()
        json.put("name",username)

        var success = false

        val thread = Thread {
            val httpURLConnection: HttpURLConnection = URL(URL + "register").openConnection() as HttpURLConnection
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
            Toast.makeText(applicationContext,"Adding a new user had gone wrong :(", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(applicationContext,"User added.", Toast.LENGTH_SHORT).show()

        finish()
    }

    private fun onBackButtonClick(){
        finish()
    }
}