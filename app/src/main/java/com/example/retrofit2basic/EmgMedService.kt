package com.example.retrofit2basic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EmgMedService {
    @GET("EmgMedInfo")
    fun getEmgMedData(@Query("KEY") KEY : String,
                      @Query("Type") Type : String): Call<EmgMedResponse>

}

