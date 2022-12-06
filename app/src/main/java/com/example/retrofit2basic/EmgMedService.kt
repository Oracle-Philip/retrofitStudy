package com.example.retrofit2basic

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EmgMedService {
    @GET("EmgMedInfo")
    fun getEmgMedData(@Query("KEY") KEY : String,
                      @Query("Type") Type : String): Call<EmgMedResponse>

    //Coroutine으로 작동하는 서비스가 인터페이스에 추가되었다.
    @GET("EmgMedInfo")
    suspend fun getDataCoroutine(
        @Query("KEY") KEY : String,
        @Query("Type") Type : String): Response<EmgMedResponse>
}

