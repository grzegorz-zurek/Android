package com.example.zad8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class RegisterActivity : AppCompatActivity() {
    private lateinit var URL: String
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var buttonLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextUsername = findViewById(R.id.registerUsername)
        editTextPassword = findViewById(R.id.registerPassword)
        editTextConfirmPassword = findViewById(R.id.registerConfirmPassword)
        buttonRegister = findViewById(R.id.btnRegister)
        buttonLogin = findViewById(R.id.btnBackToLogin)

        URL = intent.getStringExtra("URL").toString()

        buttonRegister.setOnClickListener {
            onRegisterButtonClick()
        }
        buttonLogin.setOnClickListener {
            onLoginButtonClick()
        }
    }

    private fun onRegisterButtonClick(){
        val username = editTextUsername.text.toString()
        val password = editTextPassword.text.toString()
        val confirmPassword = editTextConfirmPassword.text.toString()

        if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(applicationContext, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }
        if(password != confirmPassword){
            Toast.makeText(applicationContext,"Passwords do not match",Toast.LENGTH_SHORT).show()
            return
        }

        val json = JSONObject()
        json.put("name",username)
        json.put("password",password)

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
            Toast.makeText(applicationContext,"Registration had gone wrong :(",Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(applicationContext,"User registered.",Toast.LENGTH_SHORT).show()

        finish()
    }

    private fun onLoginButtonClick(){
        finish()
    }
}