package com.example.mdindiaandroidmachinetest.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mdindiaandroidmachinetest.R
import com.example.mdindiaandroidmachinetest.model.data.Hospital


class HospitalAdapter(private val hospitals: List<Hospital>, private val context: Context) :
    RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    class HospitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hospitalName: TextView = itemView.findViewById(R.id.tvHospitalName)
        val hospitalAddress: TextView = itemView.findViewById(R.id.tvHospitalAddress)
        val hospitalPinCode: TextView = itemView.findViewById(R.id.tvHospitalPinCode)
        val hospitalContact: TextView = itemView.findViewById(R.id.tvHospitalContact)
        val hospitalLocation: ImageView = itemView.findViewById(R.id.imgHospitalLocation)
        val cardView: CardView = itemView.findViewById(R.id.main)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hospital, parent, false)
        return HospitalViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "QueryPermissionsNeeded")
    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val hospital = hospitals[position]
        holder.hospitalName.text = hospital.HospitalName
        holder.hospitalAddress.text = hospital.HospitalAddress
        holder.hospitalPinCode.text = "Pin code : ${hospital.Pin_No}"
        holder.hospitalContact.text =
            "Contact Details : \nEmail : " + hospital.Contact_EMail + "\nPhone : " + hospital.Contact_Mobile_No


        holder.hospitalLocation.setOnClickListener {
            if (hospital.Latitude == null || hospital.Longitude == null) {
                Toast.makeText(context, "Location not available", Toast.LENGTH_SHORT).show()
            }
            hospital.Latitude?.let { it1 ->
                hospital.Longitude?.let { it2 ->
                    openGoogleMaps(it1, it2)
                }
            }
        }
        when (position % 3) {
            0 -> holder.cardView.setCardBackgroundColor(Color.RED)
            1 -> holder.cardView.setCardBackgroundColor(Color.parseColor("#008000"))
            2 -> holder.cardView.setCardBackgroundColor(Color.BLUE)
        }
    }

    override fun getItemCount(): Int {
        return hospitals.size
    }
    @SuppressLint("QueryPermissionsNeeded")
    private fun openGoogleMaps(latitude: String, longitude: String) {
        val mapUri = Uri.parse("https://maps.google.com/maps/search/$latitude,$longitude")
        val intent = Intent(Intent.ACTION_VIEW, mapUri)
        context.startActivity(intent)
    }
}