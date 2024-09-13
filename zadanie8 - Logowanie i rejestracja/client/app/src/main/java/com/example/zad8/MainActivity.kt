package com.example.zad8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private var URL = "" // <- tutaj nalezy wkleic url z ngroka

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(URL[URL.length-1] != '/'){
            URL += "/"
        }

        editTextUsername = findViewById(R.id.loginUsername)
        editTextPassword = findViewById(R.id.loginPassword)
        buttonLogin = findViewById(R.id.btnLogin)
        buttonRegister = findViewById(R.id.btnGoToRegister)

        buttonLogin.setOnClickListener {
            onLoginButtonClick()
        }
        buttonRegister.setOnClickListener {
            onRegisterButtonClick()
        }
    }

    private fun onLoginButtonClick(){
        val username = editTextUsername.text.toString()
        val password = editTextPassword.text.toString()

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(applicationContext, "Both fields are required!",Toast.LENGTH_SHORT).show()
            return
        }

        val json = JSONObject()
        json.put("name",username)
        json.put("password",password)

        var access = false

        val thread = Thread {
            val httpURLConnection: HttpURLConnection =
                URL(URL + "login").openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.setRequestProperty("Content-type", "application/json")
            httpURLConnection.setRequestProperty("Accept", "application/json")
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = true

            val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
            outputStreamWriter.write(json.toString())
            outputStreamWriter.flush()

            val responseCode = httpURLConnection.responseCode
            access = responseCode == HttpURLConnection.HTTP_ACCEPTED
        }

        thread.start()

        thread.join()

        if(!access){
            Toast.makeText(applicationContext,"Wrong username or password!",Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(applicationContext,"Logged in.",Toast.LENGTH_SHORT).show()

        clearFields()
        startActivity(Intent(this,AccessGainedActivity::class.java))
    }

    private fun onRegisterButtonClick(){
        clearFields()
        val intent = Intent(this,RegisterActivity::class.java)
        intent.putExtra("URL",URL)
        startActivity(intent)
    }

    private fun clearFields(){
        editTextUsername.setText("")
        editTextPassword.setText("")
    }
}