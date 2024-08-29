package com.example.mdindiaandroidmachinetest.model.data

data class ApiResponse(
    val response: ResponseData
)
data class ResponseData(
    val Status: String,
    val data: List<Hospital>
)
data class Hospital(
    val Provider_Code: String?,
    val HospitalName: String?,
    val HospitalType: String?,
    val HospitalAddress: String?,
    val PinCode: String?,
    val Email: String?,
    val State: String?,
    val City: String?,
    val Location: String?,
    val Hospital: String?,
    val Type: String?,
    val Address: String?,
    val Pin_No: String?,
    val Tel_no: String?,
    val STD_Code: String?,
    val Fax: String?,
    val E_Mail: String?,
    val hosp_website: String?,
    val Contact_EMail: String?,
    val Contact_Mobile_No: String?,
    val Latitude: String?,
    val Longitude: String?
)
