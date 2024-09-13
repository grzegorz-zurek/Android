package com.example.zad8

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class AccessGainedActivity : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView
    private lateinit var taskList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_gained)

        taskList = resources.getStringArray(R.array.names).toCollection(ArrayList())
        adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,taskList)
        listView = findViewById(R.id.listView)
        listView.adapter = adapter
    }
}