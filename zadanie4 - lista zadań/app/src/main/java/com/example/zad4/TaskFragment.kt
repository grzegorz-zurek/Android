package com.example.zad4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class TaskFragment : Fragment() {

    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView
    private lateinit var mContext: Context
    private lateinit var TaskList: ArrayList<String>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TaskList = resources.getStringArray(R.array.names).toCollection(ArrayList())
        adapter = ArrayAdapter<String>(mContext,R.layout.task,TaskList)
        listView = view.findViewById(R.id.listView)
        listView.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }
}