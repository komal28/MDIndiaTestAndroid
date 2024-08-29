package com.example.mdindiaandroidmachinetest.model.interfaces

import com.example.mdindiaandroidmachinetest.model.data.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("getLocationList")
    fun getLocationList(
        @Query("statename") stateName: String,
        @Query("district") district: String,
        @Query("cityname") cityName: String,
        @Query("MobileUniqId") mobileUniqId: String
    ): Call<ApiResponse>
}