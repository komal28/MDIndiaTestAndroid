package com.example.mdindiaandroidmachinetest.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mdindiaandroidmachinetest.R
import com.example.mdindiaandroidmachinetest.model.interfaces.ConnectivityUtils
import com.example.mdindiaandroidmachinetest.view.adapter.HospitalAdapter
import com.example.mdindiaandroidmachinetest.viewmodel.LocationViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: LocationViewModel by viewModels()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout = swipeRefreshLayout()
        getData()
        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
    }

    private fun getData() {
        if (ConnectivityUtils.isConnectedToInternet(this)) {
            getLocationData()
        } else {
            swipeRefreshLayout.isRefreshing = false
            Toast.makeText(this, "Your phone is not connected with internet", Toast.LENGTH_LONG)
                .show()        }
    }

    private fun getLocationData() {
        val recyclerView: RecyclerView = recyclerView()
        viewModel.fetchLocationList()
        viewModel.hospitals.observe(this, Observer { hospitals ->
            hospitals?.let {
                Log.e("response success", it.toString())
                val adapter = HospitalAdapter(it, this)
                recyclerView.adapter = adapter
                swipeRefreshLayout.isRefreshing = false
            }
        })
        viewModel.error.observe(this, Observer { error ->
            error?.let {
                Log.e("response error", it)
            }
        })
    }

    private fun recyclerView(): RecyclerView {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        return recyclerView
    }

    private fun swipeRefreshLayout(): SwipeRefreshLayout {
        val refreshLayout: SwipeRefreshLayout = findViewById(R.id.refreshLayout)
        return refreshLayout
    }
}