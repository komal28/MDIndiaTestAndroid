package com.example.mdindiaandroidmachinetest.model.repository

import com.example.mdindiaandroidmachinetest.model.data.ApiResponse
import com.example.mdindiaandroidmachinetest.model.interfaces.RetrofitInstance
import retrofit2.Call

class LocationRepository {
    fun getLocationList(
        stateName: String,
        district: String,
        cityName: String,
        mobileUniqId: String
    ): Call<ApiResponse> {
        return RetrofitInstance.api.getLocationList(stateName, district, cityName, mobileUniqId)
    }
}