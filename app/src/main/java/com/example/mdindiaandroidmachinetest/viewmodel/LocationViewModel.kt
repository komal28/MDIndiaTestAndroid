package com.example.mdindiaandroidmachinetest.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mdindiaandroidmachinetest.model.data.ApiResponse
import com.example.mdindiaandroidmachinetest.model.data.Hospital
import com.example.mdindiaandroidmachinetest.model.repository.LocationRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationViewModel : ViewModel() {

    private val repository = LocationRepository()
    private val _hospitals = MutableLiveData<List<Hospital>>()
    val hospitals: LiveData<List<Hospital>> = _hospitals

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchLocationList() {
        repository.getLocationList("Maharashtra", "Pune", "Pune", "0000002")
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            if (it.response.Status == "succeed") {
                                _hospitals.postValue(it.response.data)
                            } else {
                                _error.postValue("Failed to fetch data")
                            }
                        } ?: _error.postValue("Data not available")
                    } else {
                        _error.postValue("Error: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    _error.postValue(t.message)
                }
            })
    }
}