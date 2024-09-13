package com.example.zad11

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notifyButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notifyButton = findViewById(R.id.btnNotify)

        notifyButton.setOnClickListener{
            notifyUser()
        }
    }

    private fun notifyUser(){
        val textTitle = "Nowe Produkty!"
        val textContent = "W sklepie są dostępne nowe produkty!"
        val CHANNEL_ID = "1"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, textTitle, importance).apply {
                description = textContent
            }
            notificationManager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(androidx.core.R.drawable.notification_template_icon_bg)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        notificationManager.notify(Integer.parseInt(CHANNEL_ID),builder.build())
    }
}